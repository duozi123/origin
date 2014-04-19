__author__ = 'xzhou'
import os
import re
import subprocess



#read a file an return as a string
def read_str(path):
    e = os.path.exists(path)
    content=""
    if e:
        f = file(path)
        content=f.read()
    return content
#read a file and return as a list
def read_list(path):
    content=''
    e = os.path.exists(path)
    if e:
        f = file(path)
        content=f.readlines()
        f.close()
    return content

#find a exists word which is arounded with "" and change it in a needed one
def change(path, list):
    content = read_str(path)
    for i in list:
        p = re.compile(i[0])
        e = p.search(content)
        if e:
            content = content.replace("\"" + i[0] + "\"", "\"" + i[1]+ "\"")
    return content
#deal with changes in string and get the content  in the file
#delete words like "[-...-]" and delete "{+" "+}"
def deal_str(s):
    index = s.rfind("@")
    s = s[index+2:]
    str="["

    for str in s:
        index1 = s.find("[-")
        index2 = s.find("-]")
        s = s.replace(s[index1:index2+2], "")

    s = s.replace("+}", "")
    s = s.replace("{+", "")
    words_list = s.split()
    return words_list

#get the word in ".." from change lines
def get_word(s):
    index1=s.find('"')
    index2=s.rfind('"')
    word=(s[index1+1:index2])
    return word







#main project
def different(commit_before, commit_after):
    #certain file which have changed
    filename = "D:/c/1.txt"
    commit=[]
    commits=[]
    subprocess.Popen('git log --pretty=oneline %s > D:/c/commit.txt' % filename, shell=True)#commit for certion file between two commits
    list_commit=read_list("D:/c/commit.txt")
    for i in list_commit:
        j=i[:40]
        commit.append(j) #split it only leave commit id



    subprocess.Popen(r'git log %s..%s --pretty=format:"%%H " > D:/c/commits.txt' % (commit_before, commit_after), shell=True)#all commits between two commits
    list_commits=read_list("D:/c/commits.txt")
    for i in list_commits:
        j=i[:40]
        commits.append(j) #split it only leave commit id

#find two commits for certain file
    if commit_before in commit:
        commit_first=commit_before
    else:
        flag=0
        for i in range(len(commits)-1,0,-1):
            for j in range(len(commit)-1,0,-1):
             if commits[i]==commit[j]:
                    flag=1
                    commit_first=commits[i]
                    break
            if flag==1:
                break

    flag=0
    for i in commits:
        for j in commit:
            if i==j:
                flag=1
                commit_second=i
                break
        if flag==1:
            break

#get word in two files
    subprocess.Popen('git show %s --word-diff --pretty=oneline %s > D:/c/first.txt'%(commit_first,filename),shell=True)
    str_before = read_str(r"D:/c/first.txt")
    before_list = deal_str(str_before)


    subprocess.Popen('git show %s --word-diff --pretty=oneline %s > D:/c/second.txt'%(commit_second,filename),shell=True)
    str_after = read_str(r"D:/c/second.txt")
    after_list=deal_str(str_after)

# find the word pairs which have same words before and the word in ""is different
 #and put them in a list
    str_list=[]
    for i in after_list:
        index = i.find('"')
        head = i[:index]
        for j in before_list:
            if head in j:
                if i!=j:
                    words=[get_word(j),get_word(i)]
                    str_list.append(words)

#write the changed word in certain file back
    file_path=r"d:\c\test.txt"#the test case file
    content=change(filename, str_list)
    f = open(file_path, 'w')
    try:
        f.write(content)
    finally:
        f.close()



# subprocess.Popen('cd/', shell=True)
# subprocess.Popen('d:' , shell=True)
# subprocess.Popen('cd c' , shell=True)
# subprocess.Popen('git config --global user.name "duozi123"' , shell=True)
# subprocess.Popen('git config --global user.email "zx199107@gmail.com"' , shell=True)
# #
different("39f01d8e8eab7622d068aafbca04dfd26836aac2","d2ae9ea234cb3d5f2587701be91daeedd709fd5a")





