package com.stardict.StarDictUtils.cn.star.dict.test;


import com.stardict.StarDictUtils.cn.star.dict.StarDict;
import com.stardict.StarDictUtils.cn.star.dict.Word;

public class IdxTest {

	public static void main(String[] args)  {
		StarDict dict = new StarDict();

		dict.load("D:\\work\\词典\\stardict-21cen-2.4.2\\21cen.ifo");

		System.out.println(dict.getInfo().getBookName());
		System.out.println(dict.getInfo().getAuthor());
		System.out.println("收录词数：" + dict.getInfo().getWordCount());

			Word result = dict.select("happy");
			if (result == null) {
				System.out.println("词典中尚未收录该词");
			} else {
					System.out.println(result.getExplanation());
			}
			System.out.println();
	}

}
