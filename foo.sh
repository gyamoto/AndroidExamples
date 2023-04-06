md5 foo.txt > new_foo.md5

if [ "$(diff foo.md5 new_foo.md5)" ]
then
    echo "diff exist"
else 
    echo "no diff exist"
    exit
fi

echo "continue"
dot -T png foo.txt -o foo.png
mv new_foo.md5 foo.md5

git config --global user.name 'foo-user'
git add foo.*
git commit -m 'update foo.* [ci skip]'
