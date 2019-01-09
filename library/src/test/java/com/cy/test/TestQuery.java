package com.cy.test;

import com.cy.util.UFile;
import com.mdict.knziha.plod.dictionary.mdict;
import com.mdict.knziha.rbtree.RBTree_additive;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TestQuery {

    @Test
    public void testQueryOne() throws IOException {
        System.out.println(new File("").getAbsolutePath());
        queryOneDict("happy");
    }

    @Test
    public void testQueryMultiDict() throws IOException {
        queryMultiDict("happy");
    }

    private static final String sDict1 = "牛津英汉汉英/最终的短语测试牛津英汉汉英词典.mdx";
    private static final String sDict2 = "新牛津英汉双解大词典（第2版）/新牛津英汉双解大词典（第2版）.mdx";
    private static final String sRes2 = "新牛津英汉双解大词典（第2版）/新牛津英汉双解大词典（第2版）.mdd";


    private static void queryOneDict(String key) throws IOException {
        mdict md = new mdict(sDict1);
        int search_result = md.lookUp(key, true);//true means to match strictly
        if(search_result!=-1){
            String html_contents = md.getRecordAt(search_result);
            String entry_name = md.getEntryAt(search_result);

            UFile.write_UTF8(new File(entry_name+".html"),html_contents);
        }


//        mdictRes m1 = new mdictRes(sRes2);
        //m.printAllKeys();
//        com.cy.mdict.UtilMy.show(m1.getEntryAt(m1.lookUp(key)));

    }

    private static void queryMultiDict(String key) throws IOException {
        ArrayList<mdict> mdxs = new ArrayList<mdict>();
        mdict md1 = new mdict(sDict1);
        mdict md2 = new mdict(sDict2);
        mdxs.add(md1);
        mdxs.add(md2);
        RBTree_additive combining_search_tree = new RBTree_additive();
        for(int i=0;i<mdxs.size();i++)
        {
            mdxs.get(i).size_confined_lookUp5(key,combining_search_tree,i,30);
        }
        combining_search_tree.inOrder();//print results stored in the RBTree

        combining_search_tree.print();
        System.out.println(combining_search_tree);
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
}
