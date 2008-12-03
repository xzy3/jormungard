syntax match Error ";$"
syntax match Error "^\s*def\s\+\w\+(.*)\s*$"
syntax match Error "^\s*class\s\+\w\+(.*)$"
syntax match Error "^\s*for\s.*[^:]$"
syntax match Error "^\s*finally[^:]$"
syntax match Error "^\s*except\s.*[^:]$"
syntax match Error "^\s*try[^:]$"
syntax match Error "^\s*else[^:]$"
syntax match Error "^\s*if\s.*[^:]$"
syntax match Error "^\s*elif\s.*:$"

syntax keyword Error do
