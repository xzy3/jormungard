set nocompatible " use Vim defaults not vi defaults
syntax on

set fileformat=unix "most modern texteditors can figure this out

set lazyredraw "dont redraw while running macros

"display the current mode and partally typed commands on the status line:
set showmode
set showcmd

abbrev *copy* <esc>:call append(line(".") - 1, strftime("Copyright (C) %Y Seth Sims"))<cr>i

match spellRare /[ \t\r]\+$/

"turn off the annoying flashing and beeping on errors
set t_vb= "no flash on screen error
set visualbell
set noerrorbells "be quiet

set backspace=2 "allow backspacing over insertion point

set incsearch "jumps to search word as you type
set wildignore=*.o,*.obj,*.bak*.exe
set wildignore+=*/CVS/,*/SVN/
set wildmenu "enable menu of completions
set wildmode=longest:full,full "complete as much as is common, then menu

set undolevels=1000 "lots of undo levels

colorscheme pablo

"change to the directory of the file im editing
autocmd BufEnter * lcd %:p:h

"source the .vimrc file when it is saved
autocmd! bufwritepost .vimrc source $HOME/.vimrc
autocmd! bufwritepost _vimrc source $HOME/_vimrc

"no need to backup subversion commit logs
autocmd! BufRead svn-commit.tmp :setlocal nobackup

" Text Formating
set nowrap "dont break lines

set shiftwidth=4
set autoindent
set shiftround

" file type specific text formating
" enable filetype detection
filetype on

autocmd! FileType c,cpp,java call CStyle()
autocmd! FileType make call MakeStyle()
autocmd! FileType python call PythonStyle()
autocmd! FileType sh call ShStyle()

function! CommentBind(replacement)
	execute "map " a:replacement
	ounmap 
endfunction

function! ProgramStyle()
	set tabstop=4
	set softtabstop=4 "make 4 spaces act like tabs
	set expandtab
	set smarttab

	set showmatch "show matching ( ) and { }

	"kill trailing whitespace
	autocmd! BufWritePre * :%s/[ \t\r]\+$//e

	"search up the directory tree for an exuberent ctags file
	"but only 4 levels
	set tags=tags;../../../../
endfunction "ProgramStyle

function! CStyle()
	call ProgramStyle()

	call CommentBind(':s~^[ ]\\{0,2}~//~<CR>')

	set cindent
	set formatoptions+=ro
endfunction "CStyle

function! MakeStyle()
	call ProgramStyle()

	call CommentBind(':s/^[ ]\\?/#/<CR>')

	set noexpandtab
	set autoindent
	set list
	set lcs=tab:->
endfunction "MakeStyle

function! PythonStyle()
	call ProgramStyle()

	call CommentBind(':s/^[ ]\\?/#/<CR>')

	abbrev #! #!/usr/bin/python

	set list
	set lcs=tab:->
	set smartindent cinwords=if,elif,else,for,while,try,except,finally,def,class
	set smartindent
endfunction "PythonStyle

function! ShStyle()
	call ProgramStyle()
	call CommentBind(':s/^[ ]\\?/#/<CR>')

	abbrev #! #!/bin/bash

endfunction "ShStyle


if $COMSPEC != ""

	"windows stuff

else
	"linux stuff
	autocmd! BufWritePost *.py,*.bf,*.sh !chmod u+x %

endif


