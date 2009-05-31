if exists("b:did_ftplugin1") | finish | endif
let b:did_ftplugin1 = 1

call ProgramStyle()

setlocal makeprg=ruby

if OsName() == "linux"
    autocmd! BufWritePost silent !chmod o+x %
endif
