if exists("b:did_ftplugin1") | finish | endif
let b:did_ftplugin1 = 1

call ProgramStyle()
call CommentBind(':s/^[ ]\?/#/')

abbrev #! #!/usr/bin/python

setlocal list
setlocal lcs=tab:..
setlocal smartindent cinwords=if,elif,else,for,while,try,except,finally,def,class
setlocal smartindent

setlocal makeprg=python\ -c\ \"import\ py_compile,sys;\ sys.stderr=sys.stdout;\ py_compile.compile(r'%')\"
setlocal efm=%C\ %.%#,%A\ \ File\ \"%f\"\\,\ line\ %l%.%#,%Z%[%^\ ]%\\@=%m

if OsName() == "linux"
    autocmd! BufWritePost silent !chmod o+x %
endif
