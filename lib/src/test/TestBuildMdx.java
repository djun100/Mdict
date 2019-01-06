package test;

import com.knziha.plod.dictionary.mdict;
import com.knziha.plod.dictionaryBuilder.mdictBuilder;

import java.io.IOException;

/**
 * TEsts
 * @author KnIfER
 * @date 2018/05/31
 */
public class TestBuildMdx {
	
    static int d=0;
    public static void main(String[] args) throws IOException{
    	mdictBuilder mdxBD = new mdictBuilder("hellowrld","ooooo","UTF-8");
    	mdxBD.insert("happy", "happy means I possess you");
    	mdxBD.insert("sad", "sad means I lose you");
    	mdxBD.insert("aasadad", "sad means I lose you");
    	mdxBD.insert("parenthesis", "sad means I lose you");
    	mdxBD.insert("dianthus", "sad means I lose you");
    	mdxBD.insert("dasdad", "sad means I lose you");
    	mdxBD.insert("eaasdd", "sad means I lose you");
    	mdxBD.insert("fsdad", "sad means I lose you");
    	mdxBD.insert("gasdad", "sad means I lose you");
    	mdxBD.insert("haasddd", "sad means I lose you");
    	mdxBD.insert("iaasdadd", "sad means I lose you");
    	mdxBD.insert("jaasdsadd", "sad means I lose you");
    	for(int i=0;i<1024;i++) mdxBD.insert(UtilRandom.getRandomString2(10), "randomly lose heart megamegamega");
    	
    	
    	mdxBD.write("mdict/123Test.mdx");
    	
    	mdict md = new mdict("mdict/123Test.mdx");//en-irish.mdx
    	md.printDictInfo();
    	md.printAllKeys();
    	//md.printAllContents();
    	UtilMy.show(md.getRecordAt(md.lookUp("happy")));
    	UtilMy.show(md.getRecordAt(1024));
    	//UtilMy.show(md.getRecordAt(md.lookUp("zz7n5uibth")));
    	//UtilMy.show(md.getEntryAt(100));
    	//UtilMy.show(md.lookUp("happy")+"");
    	//UtilMy.show(mdxBD.data_tree.xxing(new myCpr("happy","")).getKey().key);
    	//UtilMy.show(md.getEntryAt(534));
    	//md.printAllContents();
    	//md.prepareItemByKeyInfo(7);
    }
}


