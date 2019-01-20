package com.cy.mdict;

import android.text.TextUtils;

import com.cy.File.UtilFile;
import com.cy.FuzzyWord;
import com.cy.data.UtilCollection;
import com.mdict.knziha.rbtree.RBTree_additive;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MdictUtil {

    public static List<FuzzyWord> queryFuzzyWordWithTrans(String word, List<Mdict> mdicts) throws IOException {
        List<String> fuzzyWords=queryFuzzyWord(word,mdicts);
        List<FuzzyWord> fuzzyWordWithTrans=new ArrayList<>();
        if (UtilCollection.isEmpty(fuzzyWords)){
            return fuzzyWordWithTrans;
        }else {
            for (String tempWord:fuzzyWords){
                FuzzyWord fuzzyWord=new FuzzyWord(tempWord,queryWordDetail(tempWord,mdicts.get(0)));
                fuzzyWordWithTrans.add(fuzzyWord);
            }
            return fuzzyWordWithTrans;
        }
    }

    public static List<String> queryFuzzyWord(String word,List<Mdict> mdicts) {
        RBTree_additive combining_search_tree = new RBTree_additive();
        for(int i=0;i<mdicts.size();i++)
        {
            mdicts.get(i).size_confined_lookUp5(word,combining_search_tree,i,30);
        }
        combining_search_tree.inOrder();//print results stored in the RBTree

        List<String> results = combining_search_tree.print();
        return results;
/*printed results looks like 【happy____@398825@0@16905@1@16906@1】...【other results】...
how to handle:
String
html_contents0 = mdxs.get(0).getRecordAt(398825),
html_contents1 = mdxs.get(1).getRecordAt(16905),
html_contents2 = mdxs.get(1).getRecordAt(16906);
...
...
*/
    }

    public static String queryWordDetail(String word,Mdict dict) throws IOException {
        int search_result = dict.lookUp(word, true);//true means to match strictly
        if(search_result!=-1){
            String html_contents = dict.getRecordAt(search_result);
            String entry_name = dict.getEntryAt(search_result);

            html_contents=TextUtils.isEmpty(html_contents)?"":html_contents;
            if (TextUtils.isEmpty(dict.getCssPathName())){
                return html_contents;
            }else {
                return "<style type=\"text/css\">" +
                        UtilFile.read_UTF8_FileContent(new File(dict.getCssPathName())) +
                        "</style>" +
                        html_contents;
            }

        }
        return "";
    }
}
