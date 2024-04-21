#!/bin/sh
set -e

currentPath=$(pwd)
scriptDirPath=$(cd $(dirname $0); pwd) # 参考: https://qiita.com/rog-works/items/c80b61997b812052873d
scriptPath="${scriptDirPath}}/set-version.jsh"

echo 意図しないパスで実行された場合はエラーになる
cd $scriptDirPath
if ! jshell set-version.jsh; then
    echo clear
fi
echo ''
cd $currentPath


echo 引数が未指定
if ! jshell $scriptPath; then
    echo clear
fi
echo ''

echo 引数の数が不足している
if ! jshell -R-Dargs="1 2" $scriptPath; then
    echo clear
fi
echo ''

echo 引数の数が多すぎる
if ! jshell -R-Dargs="1 2 3 4" $scriptPath; then
    echo clear
fi
echo ''


echo 第１引数が文字
if ! jshell -R-Dargs="a 2 3" $scriptPath; then
    echo clear
fi
echo ''

echo 第１引数が小数
if ! jshell -R-Dargs="0.1 2 3" $scriptPath; then
    echo clear
fi
echo ''

echo 第１引数が負の正数
if ! jshell -R-Dargs="-1 2 3" $scriptPath; then
    echo clear
fi
echo ''


echo 第２引数が文字
if ! jshell -R-Dargs="1 b 3" $scriptPath; then
    echo clear
fi
echo ''

echo 第２引数が小数
if ! jshell -R-Dargs="1 0.2 3" $scriptPath; then
    echo clear
fi
echo ''

echo 第２引数が負の整数
if ! jshell -R-Dargs="1 -2 3" $scriptPath; then
    echo clear
fi
echo ''

echo 第２引数が３桁
if ! jshell -R-Dargs="1 234 3" $scriptPath; then
    echo clear
fi
echo ''


echo 第３引数が文字
if ! jshell -R-Dargs="1 2 c" $scriptPath; then
    echo clear
fi
echo ''

echo 第３引数が小数
if ! jshell -R-Dargs="1 2 0.3" $scriptPath; then
    echo clear
fi
echo ''

echo 第３引数が負の整数
if ! jshell -R-Dargs="1 2 -3" $scriptPath; then
    echo clear
fi
echo ''

echo 第３引数が３桁
if ! jshell -R-Dargs="1 2 345" $scriptPath; then
    echo clear
fi
echo ''
