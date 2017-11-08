# Simple Search Engine Overview

Simply Search Engine is a simple search engine written in Java. It provides a possibility to search list of documents(represented by String) and returns a list of matching documents sorted by TF-IDF.

# Assumptions

  - List of matching documents is sorted in descneding order.
  - Documents should match following pattern defined in DataLoader for example "Document 1: \"the brown fox jumped over the brown dog\"
  - All terms are lower case.

# TF-IDF Assumptions
The tf–idf is the product of two statistics, term frequency and inverse document frequency. Various ways for determining the exact values of both statistics exist.
I chose following variants:
- raw count for TF	
- inverse document frequency for IDF

# Main Classes

  | Class | Description |
  | ------ | ------ |
| DataLoader | responsible for parsing Document from input Stream based on pattern |
| Document | represents single document |
| DocumentBuilder | responsible for creating Documents, initialization logic inside |
| DocumentTfIdfComparator | provides Comparator interface with TfIdf logic inside, sorting is descending |
| SearchEngine| Main Search class |

# Search Engine

It provides three main methods:
- addDocument(Document document)
- addDocuments(List<Document> documents)
- List<Document> search(String word)

# Usage

In order to test engine please modify CustomTest class.
There are two methods customTestUsingDataLoader() and customTestUsingDocumentBuilder().
 - You can either use DataLoader and follow defined pattern ("Document 1: \"the brown fox jumped over the brown customWord customWord dog").
 - Or you can use customTestUsingDocumentBuilder(), buildDocument(String name, String Body) provides a possibility to seperate documentName and documentBody.
 
Both methods returns a list of matching documents sorted by TF-IDF in descending order.

# Run

You need java 8 and maven to run this app. 

mvn clean install

### Todos

 - Possibility to add file from console
 - Add log4j
 - Engine should not be case sensitive.

**Have fun :)**

