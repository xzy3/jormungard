# Copyright (C) 2007 Seth Sims

# Set the environment variables
export MAILCHECK=0
export EDITOR=vim
export VISUAL=$EDITOR
export PAGER=/usr/bin/less
export CVS_RSH=ssh

umask 033 	      # Default file perm rwxr__r__

shopt -s cdspell      #fix simple spelling errors
shopt -s nocaseglob   #case insensitive file globbing
shopt -s checkwinsize #reset ROWS and COLUMS after every command
shopt -s histappend   #append history dont overwrite

alias ls='ls --color=auto'

case "$TERM" in
	'xterm')
	#	xtermControl --bg='rgb:/0/0/0' --fg='rgb:/CC/CC/CC';
	;;
esac

#set our prompt
PS1="[\t] \u@\h \w\n\$ "
