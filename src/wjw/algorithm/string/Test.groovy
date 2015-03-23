package wjw.algorithm.string

import groovy.transform.CompileStatic

import com.eaio.stringsearch.BNDM
import com.eaio.stringsearch.BoyerMooreHorspool
import com.eaio.stringsearch.BoyerMooreHorspoolRaita
import com.eaio.stringsearch.ShiftOrMismatches
import com.eaio.stringsearch.StringSearch

String txt = (new File($/z:\Docker≤‚ ‘± º«.md/$)).getText("GBK");
test1(txt,"345");

@CompileStatic
void test1(String txt,String pat) {
	long beginTime = System.nanoTime();
	long endTime = System.nanoTime();
	long pos;
	StringSearch bm;

	beginTime = System.nanoTime();
	pos = txt.indexOf(pat);
	endTime = System.nanoTime();
	println "pos:" + pos+" indexOf:" + (endTime - beginTime);

	bm = new BoyerMooreHorspool();
	pos = bm.searchString(txt,pat);
	beginTime = System.nanoTime();
	pos = bm.searchString(txt,pat);
	endTime = System.nanoTime();
	println "pos:" + pos+" BoyerMooreHorspool:" + (endTime - beginTime);

	bm = new BoyerMooreHorspoolRaita();
	pos = bm.searchString(txt,pat);
	beginTime = System.nanoTime();
	pos = bm.searchString(txt,pat);
	endTime = System.nanoTime();
	println "pos:" + pos+" BoyerMooreHorspoolRaita:" + (endTime - beginTime);

	bm = new BNDM();
	pos = bm.searchString(txt,pat);
	beginTime = System.nanoTime();
	pos = bm.searchString(txt,pat);
	endTime = System.nanoTime();
	println "pos:" + pos+" BNDM:" + (endTime - beginTime);

	bm = new ShiftOrMismatches();
	pos = bm.searchString(txt,pat);
	beginTime = System.nanoTime();
	pos = bm.searchString(txt,pat);
	endTime = System.nanoTime();
	println "pos:" + pos+" ShiftOrMismatches:" + (endTime - beginTime);
}