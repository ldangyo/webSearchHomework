/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.lucene.demo;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.lang.Integer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.index.Term;

/** Simple command-line based search demo. */
public class SimpleMetrics {

    private SimpleMetrics() {}

    /** Simple command-line based search demo. */
    public static void main(String[] args) throws Exception {

        IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get("index")));

        BufferedReader in = null;
        in = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

        while (true) {
            System.out.println("Enter query: ");

            String line = in.readLine();

            if (line == null || line.length() == -1) {
                break;
            }

            line = line.trim();
            if (line.length() == 0) {
                break;
            }

            String[] queryList = line.split("\\s+");

            Date start = new Date();
            for (int j = 0; j < queryList.length; j++) {
                Term queryTerm = new Term("contents", queryList[j]);
                try {
                    System.out.println("Word:" + queryTerm.text());
                    int df = reader.docFreq(queryTerm);
                    long tf = reader.totalTermFreq(queryTerm);
                    System.out.println("Document Frequency " + df );
                    System.out.println("Term Frequency" + tf );
                } catch (Exception e) {
                    System.out.println("Error");
                }
            }
        }

        reader.close();
    }

}
