package com.example.demo.util.search;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import static org.apache.lucene.util.Version.LATEST;

public class Operation {
    List<HouseBean> houseBeanList = new ArrayList<HouseBean>();

    public List<HouseBean> getList(){
        SqlConnection sqlConnection= new SqlConnection();
        sqlConnection.TheSqlConnection();

        return sqlConnection.selectAll();
    }


    public void buildIndex(){
        houseBeanList = getList();
        IndexWriter writer = null;
        FSDirectory directory = null;

        try {
            Analyzer analyzer = new IKAnalyzer();
            directory = FSDirectory.open(FileSystems.getDefault().getPath(Constants.MODEL_PATH));
            IndexWriterConfig config = new IndexWriterConfig(analyzer);
            writer = new IndexWriter(directory, config);
            writer.deleteAll();
        } catch (IOException e) {
            e.printStackTrace();
        }


        for (HouseBean house : houseBeanList) {
            Document doc = new Document();
//            System.out.println("address:"+house.getAddress());
            try {
                Field address = new Field("address", house.getAddress(), TextField.TYPE_STORED);
//                Field remark = new Field("remark", house.getRemarks(), TextField.TYPE_STORED);
                doc.add(address);
//                doc.add(remark);
                writer.addDocument(doc);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void search(String[] query){
        FSDirectory directory = null;
        try {
            directory = FSDirectory.open(FileSystems.getDefault().getPath(Constants.MODEL_PATH));
            IndexReader indexReader = DirectoryReader.open(directory);
            IndexSearcher indexSearcher = new IndexSearcher(indexReader);
//            TermQuery query = new TermQuery(new Term("address", "广东深圳滨海之窗3栋"));
            Analyzer analyzer=new IKAnalyzer();

//            String[] query = {"3栋"};
            String[] fields = Constants.getIOMap().get("input");///被索引的可查找的列
            int num_clause = fields.length;
            BooleanClause.Occur[] flags = new BooleanClause.Occur[num_clause];
            for (int i = 0; i < num_clause; i++) {
                flags[i] = BooleanClause.Occur.SHOULD;///OR
            }
            Query queryParser = MultiFieldQueryParser.parse(query, fields,flags, analyzer);

            TopDocs docs = indexSearcher.search(queryParser, 10);
            System.out.println(docs.scoreDocs.length);
//            System.out.println("查询总记录数为:"+docs.totalHits);

            System.out.println("address: "+query[0]);
//            System.out.println("description: "+query[1]);
            System.out.println("******* Results: ********");
            for (ScoreDoc scoreDoc : docs.scoreDocs) {
                Float score = scoreDoc.score;
                //得到文档
                int id = scoreDoc.doc;
                Document doc = indexSearcher.doc(id);

                System.out.println("address:"+doc.get("address") + " + \nscore:" +score);
            }
            // 7.  关闭IndexReader对象
            indexReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    public List<String> getAddresses(String[] query){
        FSDirectory directory = null;
        try {
            directory = FSDirectory.open(FileSystems.getDefault().getPath(Constants.MODEL_PATH));
            IndexReader indexReader = DirectoryReader.open(directory);
            IndexSearcher indexSearcher = new IndexSearcher(indexReader);
//            TermQuery query = new TermQuery(new Term("address", "广东深圳滨海之窗3栋"));
            Analyzer analyzer=new IKAnalyzer();

//            String[] query = {"3栋"};
            String[] fields = Constants.getIOMap().get("input");///被索引的可查找的列
            int num_clause = fields.length;
            BooleanClause.Occur[] flags = new BooleanClause.Occur[num_clause];
            for (int i = 0; i < num_clause; i++) {
                flags[i] = BooleanClause.Occur.SHOULD;///OR
            }
            Query queryParser = MultiFieldQueryParser.parse(query, fields,flags, analyzer);

            TopDocs docs = indexSearcher.search(queryParser, 10);
            System.out.println(docs.scoreDocs.length);
//            System.out.println("查询总记录数为:"+docs.totalHits);

            System.out.println("address: "+query[0]);
            List<String> result = new ArrayList<>();
//            System.out.println("description: "+query[1]);
            System.out.println("******* Results: ********");
            for (ScoreDoc scoreDoc : docs.scoreDocs) {
                Float score = scoreDoc.score;
                //得到文档
                int id = scoreDoc.doc;
                Document doc = indexSearcher.doc(id);

                System.out.println("address:"+doc.get("address") + " + \nscore:" +score);
                result.add(doc.get("address"));
            }
            // 7.  关闭IndexReader对象
            indexReader.close();
            return result;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }


    }



}
