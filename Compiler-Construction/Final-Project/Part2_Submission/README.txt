Project Part 2

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
  java -cp .:antlr-4.13.1-complete.jar GitActionsCompiler ../samples/python-pipeline-minimal.gha

Sample Inputs:
----
All samples are in the samples/ directory.

  python-pipeline-minimal.gha - valid workflow, expect: Compilation SUCCESS, .yml emitted
  semantic-invalid.gha - triggers all implemented semantic checks
  syntax-invalid.gha - malformed syntax for parser validation

Semantic checks:
----
Five checks are implemented in SemanticAnalyser.java:

  1. Duplicate job names [ERROR]
  2. Undefined job reference in needs [ERROR]
  3. Invalid trigger events [WARNING]
  4. Circular job dependencies [ERROR]
  5. Dangerous run commands [WARNING]

Warnings do not fail compilation. Errors cause exit code 1 and prevent YAML emission.

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
  YAMLEmitter.java
  GitActionsCompiler.java

Notes
----
- .gha is the source file extension; the compiler emits .yml files for valid inputs
- ANTLR-generated files are not included; regenerate them before compiling
- The .g4 grammar is indentation-insensitive; whitespace is skipped