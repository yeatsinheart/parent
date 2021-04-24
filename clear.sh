find ./  -name 'target' -type  d   | xargs rm -rf
find ./  -name '*.iml' -type  f   | xargs rm -rf
find ./  -name 'dist' -type  d   | xargs rm -rf
find ./  -name '.DS_Store' -type  f   | xargs rm -rf
find ./  -name '.idea/' -type  d   | xargs rm -rf
find ./  -name '.vscode/' -type  d   | xargs rm -rf