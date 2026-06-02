grammar gitActions;

// ex of parse:
// workflow "Python Pipeline"
// on push, pull_request
// job "lint"
//   runs_on ubuntu-latest
//   steps
//     run "pip install flake8"
//     run "flake8 src/"
programme : workflowDeclaration onClause jobDeclaration+ EOF ;

// captures workflow declaration like:
// workflow "Python Pipeline"
// and onClause captures trigger prefix like:
// on push, pull_request
workflowDeclaration : WORKFLOW STR ;
onClause : ON eventList ;
eventList : IDENT (COMMA IDENT)* ;

// each job has a name, optional needs, runner, and steps block
jobDeclaration : JOB STR needsClause? runsOnClause stepsClause ;

// ex: needs "lint" or needs "lint", "build"
needsClause : NEEDS STR (COMMA STR)* ;

// ex: runs_on ubuntu-latest
runsOnClause : RUNS_ON IDENT ;

// ex:
// steps
//   run "pip install -r requirements.txt"
//   run "pytest tests/"
stepsClause : STEPS runStep+ ;
runStep : RUN STR ;

// matches literal: workflow
WORKFLOW : 'workflow' ;
JOB : 'job';
NEEDS : 'needs' ;
RUNS_ON : 'runs_on' ;
STEPS : 'steps' ;
RUN : 'run' ;

// matches literal: on
ON : 'on' ;
COMMA : ',' ;

IDENT   : [a-zA-Z_][a-zA-Z0-9_-]* ;
STR     : '"' ( '\\"' | ~["\\\r\n] )* '"' ;
WS      : [ \t\r\n]+ -> skip ;
COMMENT : '//' ~[\r\n]* -> skip ;
