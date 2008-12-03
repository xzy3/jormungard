if exists("b:did_ftplugin1") | finish | endif
let b:did_ftplugin1 = 1

call ProgramStyle()
call CommentBind(':s/^[ ]\?/#/')

abbrev #! #!/bin/bash

if OsName() == "linux"
    autocmd! BufWritePost silent !chmod u+x %
endif
