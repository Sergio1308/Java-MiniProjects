# Java-MiniProjects
This repository contains several mini-projects using Java programming language that have been completed to gain practice and skill training.
# Cryptography
This mini-project is about the basics of cryptography, the science of keeping information private and secure. It contains cryptographic systems, which were used from the Roman Empire through the early 1900s, so these ciphers have been implemented, also implemented an algorithm for breaking them. 
The project involved not only string processing, but also arrays—a way to store an indexable sequence of elements. 
Skills gained:
- combine Strings using concatenation; 
- build Strings within a Java program using StringBuilder; 
- use arrays (and some other collections like HashMap, HashSet, ArrayList) to store and manipulate collections of data; 
- refactor programs for improved organization using object-oriented principles; 
- and practice effective algorithm design.

### Project Structure
This project consists of two packages in which the logic of the following decisions is implemented: 
- Caesar Cipher encryption and decryption (with 1 and 2 keys), Caesar Cipher breaker (1 and 2 keys) and some others helpful methods & classes which helps to encrypt or decrypt message (like word lengths counter or index of max finder);
- Vigenère Cipher (encryption/decryption), Vigenère breaker, and other helpful methods. The principle of this cipher is based as a sequence of Caesar ciphers with different shift values, so it uses Caesar Cipher class implementation (for simplicity, clarity and understanding how this algorithm works it uses a new slightly extended class for this).
- Test cases implemented with JUnit
- The project structure is fully consistent with the Maven standart directory layout, the project was generated using [maven quickstart archetype](https://maven.apache.org/archetypes/maven-archetype-quickstart/) with resources folder.

The project was implemented and slightly changed (somewhere the code was optimized, somewhere the project structure was changed, somewhere unnecessary classes were removed with a non-optimal implementation of the algorithm, created for educational and demonstration purposes, and somewhere test cases were optimally implemented) thanks to the Coursera course [Java Programming: Arrays, Lists, and Structured Data](https://www.coursera.org/learn/java-programming-arrays-lists-data/) from Duke University.
# Telling Random Stories from Templates
