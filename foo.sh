
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

git add foo.*
git config --global user.name 'foo-user'
git commit -m 'update foo'
