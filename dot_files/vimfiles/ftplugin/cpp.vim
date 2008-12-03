if exists("b:did_ftplugin1") | finish | endif
let b:did_ftplugin1 = 1

call ProgramStyle()
call CommentBind(':s~^[ ]\{0,2}~//~')

set cindent
set formatoptions+=ro

