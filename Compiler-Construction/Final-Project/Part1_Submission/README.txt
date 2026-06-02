Project Part 1

CS 4713 - 901
Fabian Figueroa
SOP310

Prereqs:
----
The grader is assumed to already have:
- Java 11 or newer
- ANTLR 4 installed, or the ANTLR 4 complete JAR available locally

If ANTLR is not already installed, install it with one of these options:
- macOS: brew install antlr
- Ubuntu/Debian: sudo apt-get install antlr4
- Windows: download the ANTLR 4 complete JAR from https://www.antlr.org/download.html

How to build:
----
1. Change into the src/ directory.
2. Generate the lexer and parser from gitActions.g4:
   - antlr4 -visitor gitActions.g4
   - antlr -visitor gitActions.g4
   - java -jar /path/to/antlr-4.x-complete.jar -visitor gitActions.g4
3. Compile the Java files.
   - macOS/Linux/WSL:
     javac -cp .:/path/to/antlr-4.x-complete.jar *.java
   - Windows cmd/PowerShell:
     javac -cp .;C:\path\to\antlr-4.x-complete.jar *.java

How to run:
----
macOS/Linux/WSL:
  java -cp .:/path/to/antlr-4.x-complete.jar GitActionsCompiler <input.gha>

Windows:
  java -cp .;C:\path\to\antlr-4.x-complete.jar GitActionsCompiler <input.gha>

Example:
  java -cp .:antlr-4.13.1-complete.jar GitActionsCompiler ../samples/python-pipeline.gha

Sample Inputs:
----
All samples are in the samples/ directory.

  python-pipeline.gha - valid workflow, expect: Compilation SUCCESS
  undefined-needs.gha - job references a needs target that does not exist
  duplicate-jobs.gha - two jobs share the same name
  invalid-trigger.gha - on clause contains an unrecognised event name
  cycle.gha - job A needs B, job B needs A
  dangerous-run.gha - run step contains rm -rf

Semantic checks:
----
Five checks are implemented in SemanticAnalyser.java:

  1. Duplicate job names [ERROR]
  2. Undefined job reference in needs [ERROR]
  3. Invalid trigger events [WARNING]
  4. Circular job dependencies [ERROR]
  5. Dangerous run commands [WARNING]
     Patterns flagged: rm -rf, mkfs, dd if=, fdisk, shred, format, :/dev/

Warnings should not fail compilation. Errors cause exit code 1.

Written files:
----
  gitActions.g4
  ASTNode.java
  WorkflowNode.java
  JobNode.java
  StepNode.java
  SourceLocation.java
  ASTBuilderVisitor.java
  SemanticAnalyser.java
  GitActionsCompiler.java

Notes
----
- .gha is the made up source file extension for this project so input files can be easily distinguished from generated .yml output
- ANTLR-generated files are not included; regenerate them before compiling
- The .g4 grammar is indentation-insensitive; whitespace should be skipped entirely