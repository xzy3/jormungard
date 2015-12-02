## Code formating (java.util.regex syntax) ##

  * `[ \t]+$` removes trailing whitespace
  * `(?<=\n\n)\n+` collapses multiple blank lines to one line
these are most useful together. first strip trailing whitespace then collapse the lines.