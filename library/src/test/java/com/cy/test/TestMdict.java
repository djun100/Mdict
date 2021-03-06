package com.cy.test;

import com.cy.mdict.UtilMy;
import com.mdict.knziha.plod.dictionary.BU;
import com.mdict.knziha.plod.dictionary.mdict;
import com.mdict.knziha.plod.dictionary.mdictRes;
import com.mdict.knziha.rbtree.RBTree_additive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.DataFormatException;


/**
 * TESTS
 * @author KnIfER
 * @date 2017/12/30
 */
public class TestMdict {
	public static long stst;//static start time

	
    //static ArrayList<Integer> fuzzy_search_result;
    static int d=0;
    public static void main(String[] args) throws IOException, DataFormatException  {
    //assign Mdx File here!
    	mdict md =  new mdict("E:\\assets\\mdicts\\高级汉语词典.mdx");//牛津高阶英汉双解词典.mdx
    	//final mdict md =  new mdict("E:\\mdict\\MDictPC\\doc\\清华大学藏战国竹简（伍）.mdx");
    	
    //A keyword to search!			//简明英汉汉英词典.mdx      古生物图鉴.mdx
    	String key = "茉";//abduco@拉丁语英语		马连鞍@有毒植物		happy@English-Chinese 
    	//com.cy.mdict.UtilMy.show(md.getRecordAt(md.lookUp("龤")));
    	//md.printDictInfo();
    	
    //A bunch of tests~

    	//![0]Basic query and extraction of contents
    	if(false)
    	{	/* false  true  */ 
    		UtilMy.show("\n\n——————————————————————basic query——————————————————————");
    		md =  new mdict("E:\\assets\\mdicts\\拉鲁斯法语词典.mdx");
    		key = "abces";
	        //com.cy.mdict.UtilMy.show("查询 "+key+" ： "+md.getEntryAt(md.lookUp(key)));
    		stst=System.currentTimeMillis();
	        UtilMy.show("结果html contents of "+key+" ： "+md.getRecordAt(md.lookUp(key)));
	        UtilMy.show("时耗 time used： "+(System.currentTimeMillis()-stst)+"ms");
	        
	        
	        //com.cy.mdict.UtilMy.show(""+md.lookUp("茉莉"));
    	}
          
        
        /*![6]Advanced mdicts conjunction search.*/
    	/*联合搜索测试*/
        if(false)
        {	/* false  true  */
	        UtilMy.show("\n\n——————————————————————searching in a bunch of dicts——————————————————————");
	        
	        ArrayList<mdict> mdxs = new ArrayList<mdict>();
	        
	        /*somewhat《English-Chinese essential》、《oxford English-Chinese advanced》*/
	        for(int i=0;i<1;i++)
	        mdxs.add(new mdict("E:\\assets\\mdicts\\简明英汉汉英词典.mdx"));
	        //mdxs.add(new mdict("C:\\antiquafortuna\\MDictPC\\doc\\牛津高阶英汉双解词典.mdx"));
	    	
	        
	    	RBTree_additive combining_search_tree = new RBTree_additive();
	    	stst=System.currentTimeMillis();
	    	
	    	key = "a and";// absolute
	    	for(int i=0;i<mdxs.size();i++)
	    	{
	    		mdxs.get(i).size_confined_lookUp5(key,combining_search_tree,i,30);
	    	}
	    	
	        UtilMy.show("时耗 Time used： "+(System.currentTimeMillis()-stst)+"ms");
	        UtilMy.show("联合搜索结果 results： ");
	        combining_search_tree.inOrder();
        }  

        
    	//![3]fuzzy search ✔
        if(false)
        {	/* false  true  */
	        UtilMy.show("\n\n—————————————————————— fuzzy search ——————————————————————");
	        
        	final boolean isCombinedSearching=true;
        	final int adapter_idx=0;
        	final ArrayList<mdict> mdicts = new ArrayList<>();
        	mdicts.add(new mdict("E:\\assets\\mdicts\\牛津高阶英汉双解词典.mdx"));
        	
	    	UtilMy.show("\r\n糊匹配测试START...");
	    	
	    	final String keyf = "c*gr.phy";
	    	stst=System.currentTimeMillis();
	    	if(isCombinedSearching) {
	    		for(mdict mdx:mdicts) {
	    			mdx.flowerFindAllKeys(keyf, 0, 30);
	    		}
	    	}else
	    		mdicts.get(adapter_idx).flowerFindAllKeys(keyf, 0, 30);
	    	harvestfuzzySearch(isCombinedSearching,adapter_idx,mdicts);
        }   
        
      //![3]fuzzy search ✔
        if(false)
        {	/* false  true  */
	        UtilMy.show("\n\n—————————————————————— fuzzy search2 ——————————————————————");
        	final boolean isCombinedSearching=true;
        	final int adapter_idx=0;
        	final ArrayList<mdict> md1 = new ArrayList<>();
        	md1.add(new mdict("E:\\assets\\mdicts\\牛津高阶英汉双解词典.mdx"         ));
        	//md1.add(new mdict("E:\\assets\\mdicts\\新日漢大辭典.mdx"                 ));
        	//md1.add(new mdict("E:\\assets\\mdicts\\NameOfPlants.mdx"                 ));
        	//md1.add(new mdict("E:\\assets\\mdicts\\ode2_raw.mdx"                     ));
        	//md1.add(new mdict("E:\\assets\\mdicts\\Irish-En.mdx"));
        	//md1.add(new mdict("E:\\assets\\mdicts\\俄汉汉俄辞典.mdx"             ));
        	
	    	UtilMy.show("\r\n糊匹配测试START...");
	    	
	    	final String keyf = "c*gr.phy";
	    	stst=System.currentTimeMillis();
	    	
	    	//!!this's being here is very important, put it in the worker-thread will cause lag
			for(int i=0;i<md1.size();i++){//遍历所有词典
				mdict mdtmp = md1.get(i);
				if(mdtmp.combining_search_tree2==null) {
				}
				else
	    		for(int ti=0;ti<mdtmp.combining_search_tree2.length;ti++){//遍历搜索结果
	    			if(mdtmp.combining_search_tree2[ti]==null) {
	    				continue;
	    			}
	    			mdtmp.combining_search_tree2[ti].clear();
	    		}
				
			}
			stst=System.currentTimeMillis(); //获取开始时间 
	    	
			for(int i=0;i<md1.size();i++){
				try {
					md1.get(i).flowerFindAllKeys(keyf,i,30);
					//publisResults();
					//if(isCancelled()) break;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	    	
	    	
	    	
			int[] firstLookUpTable = new int[md1.size()];
			int size=0;
			int resCount=0;
			for(int i=0;i<md1.size();i++){//遍历所有词典
				mdict mdtmp = md1.get(i);
				if(mdtmp.combining_search_tree2==null) {
				}
				else
	    			//if(mdtmp.combining_search_tree2!=null)
	        		for(int ti=0;ti<mdtmp.split_keys_thread_number;ti++){
	        			if(mdtmp.combining_search_tree2[ti]!=null)
	        				for(int i1=0;i1<mdtmp.combining_search_tree2[ti].size();i1++) {
	        					UtilMy.show(mdtmp.getEntryAt(mdtmp.combining_search_tree2[ti].get(i1)));
	        					resCount++;
	        				}
	        		}
				firstLookUpTable[i]=resCount;
				
			}
	    	
	    	
    		UtilMy.show("模糊搜索完成！ 耗时"+(System.currentTimeMillis()-stst)+"ms,共搜索到 "+resCount+"个词条！");
        }   
        
        
        //![6] fuzzy find in contents ✔
        if(true) 
        {	/* false  true  */
	        UtilMy.show("\n\n—————————————————————— 多线程全文搜索测试 ——————————————————————");
	    	UtilMy.show("...\r\n");
	    	key = "happy";
	    	stst=System.currentTimeMillis(); //获取STARTtime used 
	    	ArrayList<mdict> mdicts = new ArrayList<>();
	    	mdicts.add(new mdict("E:\\assets\\mdicts\\高级汉语词典.mdx"));//牛津高阶英汉双解词典.mdx
	    	for(mdict mdTmp:mdicts)
	    		mdTmp.flowerFindAllContents(key,0,30);
	        resultRecorderScattered2 gross = new resultRecorderScattered2(mdicts);
	        gross.invalidate();
	        for(int i=0;i<gross.size();i++) {
	        	UtilMy.show(gross.getResAt(i));
	        }
	        UtilMy.show("多线程全文搜索测试 time used： "+(System.currentTimeMillis()-stst)+"ms 共搜索到res count: "+gross.size());
	        
        }     
        
        //![8]
        if(false)
        {	/* false  true  */
        	mdict.stst=System.currentTimeMillis();
        	stst=System.currentTimeMillis();
	    	UtilMy.show("\r\n多线程全文搜索测试START...");
	    	key = "㑞";
	    	BU.printBytes(key.getBytes("GB18030"));
	    	stst=System.currentTimeMillis(); //获取STARTtime used 
	        md.flowerFindAllContents(key,0,0);
	        UtilMy.show("多线程全文搜索测试 time usedA： "+(System.currentTimeMillis()-stst)+"ms");
        }   
        
        //md.flowerFindAllKeys("happy", 0, 0);

        //md.prepareItemByKeyInfo(null, 19);
    	
    	//![3]very simple match middle pattern
        
        //if(false)
        //{	/* false  true  */
	    //	com.cy.mdict.UtilMy.show("\r\n糊匹配测试START...");
	    //	key = "stizo";
	    //	stst=System.currentTimeMillis();
	    //    md.findAllKeys(key);
	    //    com.cy.mdict.UtilMy.show("模糊匹配 Contain:"+key+" time used： "+(System.currentTimeMillis()-stst)+"ms");
        //} 
        ////![3.1]very simple match middle pattern
        //if(false)
        //{	/* false  true  */
	    //	com.cy.mdict.UtilMy.show("\r\n糊匹配测试START...");
	    //	key = "stizo";
	    //	stst=System.currentTimeMillis();
	    //    md.findAllKeys(key);
	    //    com.cy.mdict.UtilMy.show("模糊匹配 Contain:"+key+" time used： "+(System.currentTimeMillis()-stst)+"ms");
        //} 
        
        //mdictRes m = new mdictRes("E:\\mdict\\MDictPC\\doc\\wordsmyth2018.mdd");
        //m.printAllKeys();
    	//com.cy.mdict.UtilMy.show(":::>"+m.getEntryAt(m.lookUp("\\pic\\wolfSnow.jpg")));
    	//BU.printBytes3(m.getRecordAt(m.lookUp("\\一.tif")),"一.tif");
        
        if(false) {
	        stst = System.currentTimeMillis();
	        //for(int i=0;i<100000;i++)
	        //	md.lookUp("龤"); //entry 龤 is at end
	        
	        //for(int i=0;i<100000;i++)
	        //	md.lookUp("拭");
			UtilMy.show("模糊搜索完成! 耗时"+(System.currentTimeMillis()-stst)+"ms,共搜索到 ");
        }
        
        
        //mdict m = new mdict("E:\\assets\\mdicts\\简明英汉汉英词典.mdx");
        //com.cy.mdict.UtilMy.show(m.lookUp("a and")+"");
        
        //mdict m = new mdict("E:\\assets\\mdicts\\TCM_Textbook.mdx");
        //m.printAllKeys();
        //com.cy.mdict.UtilMy.show(m.getEntryAt(m.lookUp("\\VMpix\\ShengWuPix\\shengwuhuaxueyufenzishengwuxue279.jpg"))+"");
        //com.cy.mdict.UtilMy.show(m.getRecordAt(0)+"");
        //com.cy.mdict.UtilMy.show((m.getEntryAt(m.lookUp("\hex111111.jpg")))+"");
        //com.cy.mdict.UtilMy.show((m.getEntryAt(m.lookUp("目的")))+"");
        
        if(false) {
	        mdict m = new mdict("E:\\assets\\mdicts\\诗经集注（注解两种）\\诗经集注（注解两种）.mdx");
	        //m.printAllKeys();
	        UtilMy.show(m.getRecordAt(m.lookUp("七月"))+"");
	        
	        mdictRes m1 = new mdictRes("F:\\NVPACK\\mds\\zhwiki-20180601_V1.3.mdd");
	        //m.printAllKeys();
	        UtilMy.show(m1.getEntryAt(m1.lookUp("\\pic\\ja\\jaglion.jpg"))+"");
	        stst = System.currentTimeMillis();
	        int cc=0;
	        while(cc<1000) {
	        	m1.getRecordAt(m1.lookUp("\\pic\\ja\\jaglion.jpg"));
	        	cc++;
	        }
	        UtilMy.show("getEntryAt时间"+(System.currentTimeMillis()-stst));
	        
	        
	        
	        m = new mdict("F:\\NVPACK\\mds\\zhwiki-20180601_V1.3.mdx");
	        stst = System.currentTimeMillis();
	        cc=0;
	        while(cc<1) {
	        	for(int i=0;i<m.get_num_key_blocks()/100;i++) {
	        		m.prepareItemByKeyInfo(null, i, null);
	        	}
	        	cc++;
	        }
	        m.fetch_keyBlocksHeaderTextKeyID();
	        UtilMy.show("prepareItemByKeyInfo时间"+(System.currentTimeMillis()-stst));
        
        }
        
        
        //BU.printFile(m1.getRecordAt(m1.lookUp("\\pic\\ja\\jaglion.jpg")), 0, 0, "F:\\0tmp\\"+m1.getEntryAt(m1.lookUp("\\pic\\ja\\jaglion.jpg")).replace("\\","_"));
}
    






    public static void harvestfuzzySearch(boolean isCombinedSearching,int adapter_idx,ArrayList<mdict> mdicts) {
		int fuzzy_task_count=-10086;
			for (mdict mdI:mdicts) {
				if(fuzzy_task_count==-10086){
					if(isCombinedSearching){
						fuzzy_task_count = mdicts.size();
					}else{
						fuzzy_task_count = 1;
					}
				}
				fuzzy_task_count--;
				if(fuzzy_task_count<=0){
					int cc = 0;
					if(isCombinedSearching){
						for(int i=0;i<mdicts.size();i++){
	            			mdict mdtmp = mdicts.get(i);
	            			//if(mdtmp.combining_search_tree2!=null)
	                		for(int ti=0;ti<mdtmp.split_keys_thread_number;ti++){
	                			if(mdtmp.combining_search_tree2[ti]!=null)
	                				for(int i1=0;i1<mdtmp.combining_search_tree2[ti].size();i1++) {
	                					UtilMy.show(mdtmp.getEntryAt(mdtmp.combining_search_tree2[ti].get(i1)));
	                					cc++;
	                				}
	                		}
	                		mdtmp.combining_search_tree2 = null;
						}
					}else{//单独搜索
						mdict mdtmp;
							mdtmp = mdicts.get(adapter_idx);
							for(int ti=0;ti<mdtmp.split_keys_thread_number;ti++){
	                			if(mdtmp.combining_search_tree2[ti]!=null)
	                				for(int i1=0;i1<mdtmp.combining_search_tree2[ti].size();i1++) {
	                					UtilMy.show(mdtmp.getEntryAt(mdtmp.combining_search_tree2[ti].get(i1)));
	                					cc++;
	                				}
	                		}
						mdtmp.combining_search_tree2 = null;
					}
					
            		UtilMy.show("模糊搜索完成! 耗时"+(System.currentTimeMillis()-stst)+"ms,共搜索到 "+cc+"个词条！");
            		
					fuzzy_task_count=-10086;
				}
			}
		};


		


}


