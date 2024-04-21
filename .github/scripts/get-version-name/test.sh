#!/bin/sh
set -e

currentPath=$(pwd)
scriptDirPath=$(cd $(dirname $0); pwd) # 参考: https://qiita.com/rog-works/items/c80b61997b812052873d

echo 意図しないパスで実行された場合はエラーになる
cd $scriptDirPath
if ! jshell ${scriptDirPath}/get-version-name.jsh; then
    echo clear
fi
cd $currentPath
