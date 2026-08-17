# Compiler Construction

Course materials and assignments for Compiler Construction.

Nickle repository: [https://github.com/drz64/nickle](https://github.com/drz64/nickle)

## Overview

Compiler assignments covering ANTLR grammars, AST construction, code generation, and the Nickle ILOC toolchain.

## Folder Map

- Programming-Assignment1: ANTLR4 lexer-only XML balance checker. Validates opening/closing tag nesting using a stack, reports unmatched tags.
- Programming-Assignment2: Extended LOGO interpreter with full expression evaluator, variables, ifz conditional, and pen width command.
- Programming-Assignment3: Nickle ILOC compiler workspace with the shared Nickle runtime snapshot in `nickle/` and sample `.c` / `.iloc` inputs.
- Programming-Assignment3-Samples: Companion Nickle ILOC samples for the shared runtime and compiler exercises.
- Programming-Assignment4: ANTLR4-based front end and ILOC code generator that reads from stdin and emits translated output.
- Final-Project: Final compiler project split into Part 1 and Part 2 submissions plus the final report.

## Run / Build

- Follow the README inside the specific assignment folder when one exists.
- Grammar-based assignments usually require the ANTLR toolchain or the provided build scripts.
- Nickle-related work uses the repository snapshot under `Programming-Assignment3/nickle/`.
