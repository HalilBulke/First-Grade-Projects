a = input("Please enter feeding map as a list:\n")
direction = input("Please enter direction of movements as a list:\n")
b = []
h = a.count("]")
for n in a:
    if n == "]" or n == "," or n == " " or n == "[" or n == "'":
        pass
    else:
        b.append(n)
t = int(a.count("'")/2)
s = int(t/(h-1))
c = [b[j:j +s] for j in range(0, len(b), s)]
print("Your board is:")
for k in c:
    for t in k:
        print(t,end=" ")
    print(end="\n")
def find_rabbit():
        x = [x for x in c if "*" in x][0]
        global m
        m = [c.index(x),x.index("*")]
total = 0
def summation(element):
    global total
    if element == "C":
        total += 10
    elif element == "A":
        total += 5
    elif element == "M":
        total -= 5
    else:
        pass
    return total
def movement(director):
    for i in director:
        if i == "U":
            find_rabbit()
            if m[0] == 0 or c[m[0]-1][m[1]] == "W":
                pass
            elif c[m[0]-1][m[1]] == "P":
                c[m[0]-1][m[1]] = c[m[0]][m[1]]
                c[m[0]][m[1]] = "X"
                break
            else:
                summation(c[m[0]-1][m[1]])
                c[m[0]-1][m[1]]=c[m[0]][m[1]]
                c[m[0]][m[1]] = "X"
        elif i == "D":
            find_rabbit()
            if m[0] == len(c)-1 or c[m[0]+1][m[1]] == "W":
                pass
            elif c[m[0]+1][m[1]] == "P":
                c[m[0]+1][m[1]] = c[m[0]][m[1]]
                c[m[0]][m[1]] = "X"
                break
            else:
                summation(c[m[0]+1][m[1]])
                c[m[0]+1][m[1]]=c[m[0]][m[1]]
                c[m[0]][m[1]] = "X"
        elif i == "R":
            find_rabbit()
            if m[1] == len(c[0])-1 or c[m[0]][m[1]+1] == "W":
                pass
            elif c[m[0]][m[1]+1] == "P":
                c[m[0]][m[1]+1] = c[m[0]][m[1]]
                c[m[0]][m[1]] = "X"
                break
            else:
                summation(c[m[0]][m[1]+1])
                c[m[0]][m[1]+1] = c[m[0]][m[1]]
                c[m[0]][m[1]] = "X"
        elif i == "L":
            find_rabbit()
            if m[1] == 0 or c[m[0]][m[1]-1] == "W":
                pass
            elif c[m[0]][m[1]-1] == "P":
                c[m[0]][m[1]-1] = c[m[0]][m[1]]
                c[m[0]][m[1]] = "X"
                break
            else:
                summation(c[m[0]][m[1]-1])
                c[m[0]][m[1]-1] = c[m[0]][m[1]]
                c[m[0]][m[1]] = "X"
movement(direction)
print("Your output should be like this:")
for k in c:
    for t in k:
        print(t,end=" ")
    print(end="\n")
print("Your score is:", total)