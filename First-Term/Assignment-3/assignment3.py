import sys
f=open(sys.argv[1],"r")
commands=[[line.split()] for line in f.readlines()]
f.close()

my_table=[["R1","N1","B1","QU","KI","B2","N2","R2"],["P1","P2","P3","P4","P5","P6","P7","P8"],
          ["  ","  ","  ","  ","  ","  ","  ","  "],["  ","  ","  ","  ","  ","  ","  ","  "],
          ["  ","  ","  ","  ","  ","  ","  ","  "],["  ","  ","  ","  ","  ","  ","  ","  "],
          ["p1","p2","p3","p4","p5","p6","p7","p8"],["r1","n1","b1","qu","ki","b2","n2","r2"]]
def initialize():
    global my_table
    my_table = [["R1","N1","B1","QU","KI","B2","N2","R2"],["P1","P2","P3","P4","P5","P6","P7","P8"],
                ["  ","  ","  ","  ","  ","  ","  ","  "],["  ","  ","  ","  ","  ","  ","  ","  "],
                ["  ","  ","  ","  ","  ","  ","  ","  "],["  ","  ","  ","  ","  ","  ","  ","  "],
                ["p1","p2","p3","p4","p5","p6","p7","p8"],["r1","n1","b1","qu","ki","b2","n2","r2"]]
    print(">","initialize")
    print("OK")
    print("-----------------------")
    for i in my_table:
        for j in i:
            print(j,end=" ")
        print(end="\n")
    print("-----------------------")

H={"a1":[7,0],"a2":[6,0],"a3":[5,0],"a4":[4,0],"a5":[3,0],"a6":[2,0],"a7":[1,0],"a8":[0,0],
"b1":[7,1],"b2":[6,1],"b3":[5,1],"b4":[4,1],"b5":[3,1],"b6":[2,1],"b7":[1,1],"b8":[0,1],
"c1":[7,2],"c2":[6,2],"c3":[5,2],"c4":[4,2],"c5":[3,2],"c6":[2,2],"c7":[1,2],"c8":[0,2],
"d1":[7,3],"d2":[6,3],"d3":[5,3],"d4":[4,3],"d5":[3,3],"d6":[2,3],"d7":[1,3],"d8":[0,3],
"e1":[7,4],"e2":[6,4],"e3":[5,4],"e4":[4,4],"e5":[3,4],"e6":[2,4],"e7":[1,4],"e8":[0,4],
"f1":[7,5],"f2":[6,5],"f3":[5,5],"f4":[4,5],"f5":[3,5],"f6":[2,5],"f7":[1,5],"f8":[0,5],
"g1":[7,6],"g2":[6,6],"g3":[5,6],"g4":[4,6],"g5":[3,6],"g6":[2,6],"g7":[1,6],"g8":[0,6],
"h1":[7,7],"h2":[6,7],"h3":[5,7],"h4":[4,7],"h5":[3,7],"h6":[2,7],"h7":[1,7],"h8":[0,7]}


D={"70": "a1", "60": "a2", "50": "a3", "40": "a4", "30": "a5", "20": "a6", "10": "a7", "00": "a8",
   "71": "b1", "61": "b2", "51": "b3", "41": "b4", "31": "b5", "21": "b6", "11": "b7", "01": "b8",
   "72": "c1", "62": "c2", "52": "c3", "42": "c4", "32": "c5", "22": "c6", "12": "c7", "02": "c8",
   "73": "d1", "63": "d2", "53": "d3", "43": "d4", "33": "d5", "23": "d6", "13": "d7", "03": "d8",
   "74": "e1", "64": "e2", "54": "e3", "44": "e4", "34": "e5", "24": "e6", "14": "e7", "04": "e8",
   "75": "f1", "65": "f2", "55": "f3", "45": "f4", "35": "f5", "25": "f6", "15": "f7", "05": "f8",
   "76": "g1", "66": "g2", "56": "g3", "46": "g4", "36": "g5", "26": "g6", "16": "g7", "06": "g8",
   "77": "h1", "67": "h2", "57": "h3", "47": "h4", "37": "h5", "27": "h6", "17": "h7", "07": "h8"}

white=["p1","p2","p3","p4","p5","p6","p7","p8","r1","n1","b1","qu","b2","n2","r2"]
black=["P1","P2","P3","P4","P5","P6","P7","P8","R1","N1","B1","QU","B2","N2","R2"]

def find_location(piece):
    global location
    x = [x for x in my_table if piece in x][0]
    location = [my_table.index(x),x.index(piece)]
    return location

def showmoves(stone):
    global show
    if stone == "p1" or stone == "p2" or stone =="p3" or stone =="p4" or stone =="p5" or stone =="p6" or stone =="p7" or stone=="p8":
        find_location(stone)
        show=[]
        if my_table[location[0]-1][location[1]] == "  " or my_table[location[0]-1][location[1]] in black:
            show.append(D[str(location[0]-1)+str(location[1])])
            return show
    if stone =="P1" or stone =="P2" or stone =="P3" or stone =="P4" or stone =="P5" or stone =="P6" or stone =="P7" or stone =="P8":
        find_location(stone)
        show=[]
        if my_table[location[0]+1][location[1]] == "  " or my_table[location[0]-1][location[1]] in white:
            show.append(D[str(location[0]+ 1)+str(location[1])])
            return show
    if stone == "n2" or stone == "n1":
        find_location(stone)
        show=[]
        if str(location[0]-2)+str(location[1]-1) in D:
            if my_table[location[0]-2][location[1]-1] == "  " or my_table[location[0]-2][location[1]-1] in black:                                                                                    #not in white and my_table[location[0]-2][location[1]-1] in
                show.append(D[str(location[0]-2)+str(location[1]-1)])
        if str(location[0]-2)+str(location[1]+1) in D:
            if my_table[location[0]-2][location[1] + 1]== "  " or my_table[location[0] - 2][location[1] + 1] in black:
                show.append(D[str(location[0]-2)+str(location[1]+1)])
        if str(location[0] - 1)+str(location[1] - 2) in D:
            if my_table[location[0] - 1][location[1] - 2]== "  " or my_table[location[0] - 1][location[1] - 2] in black:
                show.append(D[str(location[0] - 1)+str(location[1] - 2)])
        if str(location[0] - 1)+str(location[1] + 2) in D:
            if my_table[location[0] - 1][location[1] + 2]=="  " or my_table[location[0] - 1][location[1] + 2] in black:
                show.append(D[str(location[0] - 1)+str(location[1] + 2)])
        if str(location[0] + 1)+str(location[1] - 2) in D:
            if my_table[location[0] + 1][location[1] - 2]=="  " or my_table[location[0] + 1][location[1] - 2] in black:
                show.append(D[str(location[0] + 1)+str(location[1] - 2)])
        if str(location[0] + 1)+str(location[1] + 2) in D:
            if my_table[location[0] + 1][location[1] + 2]=="  " or my_table[location[0] + 1][location[1] + 2] in black:
                show.append(D[str(location[0] + 1)+str(location[1] + 2)])
        if str(location[0] + 2)+str(location[1] - 1) in D:
            if my_table[location[0] + 2][location[1] - 1]=="  " or my_table[location[0] + 2][location[1] - 1] in black:
                show.append(D[str(location[0] + 2)+str(location[1] - 1)])
        if str(location[0] + 2)+str(location[1] + 1) in D:
            if my_table[location[0] + 2][location[1] + 1]=="  " or my_table[location[0] + 2][location[1] + 1] in black:
                show.append(D[str(location[0] + 2)+str(location[1] + 1)])
        if str(location[0] - 1)+str(location[1] - 1) in D:
            if my_table[location[0] - 1][location[1] - 1] == "  ":
                show.append(D[str(location[0] - 1)+str(location[1] - 1)])
        if str(location[0] - 1)+str(location[1] + 1) in D:
            if my_table[location[0] - 1][location[1] + 1] == "  ":
                show.append(D[str(location[0] - 1)+str(location[1] + 1)])
        if str(location[0] + 1)+str(location[1] + 1) in D:
            if my_table[location[0] + 1][location[1] + 1] == "  ":
                show.append(D[str(location[0] + 1)+str(location[1] + 1)])
        if str(location[0] + 1)+str(location[1] - 1) in D:
            if my_table[location[0] + 1][location[1] - 1] == "  ":
                show.append(D[str(location[0] + 1)+str(location[1] - 1)])
        return show
    if stone == "N2" or stone == "N1":
        find_location(stone)
        show=[]
        if str(location[0]-2)+str(location[1]-1) in D:
            if my_table[location[0]-2][location[1]-1] == "  " or my_table[location[0]-2][location[1]-1] in white:                                                                                    #not in white and my_table[location[0]-2][location[1]-1] in
                show.append(D[str(location[0]-2)+str(location[1]-1)])
        if str(location[0]-2)+str(location[1]+1) in D:
            if my_table[location[0]-2][location[1] + 1]== "  " or my_table[location[0] - 2][location[1] + 1] in white:
                show.append(D[str(location[0]-2)+str(location[1]+1)])
        if str(location[0] - 1)+str(location[1] - 2) in D:
            if my_table[location[0] - 1][location[1] - 2]== "  " or my_table[location[0] - 1][location[1] - 2] in white:
                show.append(D[str(location[0] - 1)+str(location[1] - 2)])
        if str(location[0] - 1)+str(location[1] + 2) in D:
            if my_table[location[0] - 1][location[1] + 2]=="  " or my_table[location[0] - 1][location[1] + 2] in white:
                show.append(D[str(location[0] - 1)+str(location[1] + 2)])
        if str(location[0] + 1)+str(location[1] - 2) in D:
            if my_table[location[0] + 1][location[1] - 2]=="  " or my_table[location[0] + 1][location[1] - 2] in white:
                show.append(D[str(location[0] + 1)+str(location[1] - 2)])
        if str(location[0] + 1)+str(location[1] + 2) in D:
            if my_table[location[0] + 1][location[1] + 2]=="  " or my_table[location[0] + 1][location[1] + 2] in white:
                show.append(D[str(location[0] + 1)+str(location[1] + 2)])
        if str(location[0] + 2)+str(location[1] - 1) in D:
            if my_table[location[0] + 2][location[1] - 1]=="  " or my_table[location[0] + 2][location[1] - 1] in white:
                show.append(D[str(location[0] + 2)+str(location[1] - 1)])
        if str(location[0] + 2)+str(location[1] + 1) in D:
            if my_table[location[0] + 2][location[1] + 1]=="  " or my_table[location[0] + 2][location[1] + 1] in white:
                show.append(D[str(location[0] + 2)+str(location[1] + 1)])
        if str(location[0] - 1)+str(location[1] - 1) in D:
            if my_table[location[0] - 1][location[1] - 1] == "  ":
                show.append(D[str(location[0] - 1)+str(location[1] - 1)])
        if str(location[0] - 1)+str(location[1] + 1) in D:
            if my_table[location[0] - 1][location[1] + 1] == "  ":
                show.append(D[str(location[0] - 1)+str(location[1] + 1)])
        if str(location[0] + 1)+str(location[1] + 1) in D:
            if my_table[location[0] + 1][location[1] + 1] == "  ":
                show.append(D[str(location[0] + 1)+str(location[1] + 1)])
        if str(location[0] + 1)+str(location[1] - 1) in D:
            if my_table[location[0] + 1][location[1] - 1] == "  ":
                show.append(D[str(location[0] + 1)+str(location[1] - 1)])
        return show

    if stone == "B1" or stone == "B2":
        find_location(stone)
        x, y, z = 0, 0, 0
        show=[]
        while x <= 7 :
            x+=1
            if str(location[0] + x)+str(location[1] + x) in D:
                if my_table[location[0] + x][location[1] + x] == "  ":
                    show.append(D[str(location[0] + x) + str(location[1] + x)])
                if my_table[location[0] + x][location[1] + x] in white:
                    show.append(D[str(location[0] + x) + str(location[1] + x)])
                    break
                if my_table[location[0] + x][location[1] + x] in black or my_table[location[0] + x][location[1] + x] == "KI":
                    break
        while z <= 7 and y >= -7:
            z+=1
            y-=1
            if str(location[0] + z)+str(location[1] + y) in D:
                if my_table[location[0] + z][location[1] + y] == "  ":
                    show.append(D[str(location[0] + z) + str(location[1] + y)])
                if my_table[location[0] + z][location[1] + y] in white:
                    show.append(D[str(location[0] + z) + str(location[1] + y)])
                    break
                if my_table[location[0] + z][location[1] + y] in black or my_table[location[0] + z][location[1] + y] == "KI":
                    break
        return show
    if stone == "b1" or stone == "b2":
        find_location(stone)
        x, y, z = 0, 0, 0
        show=[]
        while y >= -7 :
            y-=1
            if str(location[0] + y)+str(location[1] + y) in D:
                if my_table[location[0] + y][location[1] + y] == "  ":
                    show.append(D[str(location[0] + y) + str(location[1] + y)])
                if my_table[location[0] + y][location[1] + y] in black:
                    show.append(D[str(location[0] + y) + str(location[1] + y)])
                    break
                if my_table[location[0] + y][location[1] + y] in white or my_table[location[0] + y][location[1] + y] == "ki":
                    break
        while x <= 7 and z >= -7:
            z-=1
            x+=1
            if str(location[0] + z)+str(location[1] + x) in D:
                if my_table[location[0] + z][location[1] + x] == "  ":
                    show.append(D[str(location[0] + z) + str(location[1] + x)])
                if my_table[location[0] + z][location[1] + x] in black:
                    show.append(D[str(location[0] + z) + str(location[1] + x)])
                    break
                if my_table[location[0] + z][location[1] + x] in white or my_table[location[0] + z][location[1] + x] == "ki":
                    break
        return show
    if stone == "R1" or stone == "R2":
        find_location(stone)
        x, y, z, v= 0, 0, 0, 0
        show=[]
        while x <= 7:
            x+=1
            if str(location[0])+str(location[1] + x) in D:
                if my_table[location[0]][location[1] + x] == "  ":
                    show.append(D[str(location[0]) + str(location[1] + x)])
                if my_table[location[0]][location[1] + x] in white:
                    show.append(D[str(location[0]) + str(location[1] + x)])
                    break
                if my_table[location[0]][location[1] + x] in black or my_table[location[0]][location[1] + x] == "KI":
                    break

        while y >= -7:
            y-=1
            if str(location[0] )+str(location[1] + y) in D:
                if my_table[location[0]][location[1] + y] == "  ":
                    show.append(D[str(location[0]) + str(location[1] + y)])
                if my_table[location[0]][location[1] + y] in white:
                    show.append(D[str(location[0]) + str(location[1] + y)])
                    break
                if my_table[location[0]][location[1] + y] in black or my_table[location[0]][location[1] + y] == "KI":
                    break

        while z <= 7 :
            z+=1
            if str(location[0] + z)+str(location[1]) in D:
                if my_table[location[0] + z][location[1]] == "  " :
                    show.append(D[str(location[0]+ z) + str(location[1])])
                if my_table[location[0] + z][location[1]] in white:
                    show.append(D[str(location[0]+ z) + str(location[1])])
                    break
                if my_table[location[0] + z][location[1]] in black or my_table[location[0] + z][location[1]] == "KI":
                    break

        while v >= -7:
            v-=1
            if str(location[0] + v)+str(location[1]) in D:
                if my_table[location[0] + v][location[1]] == "  ":
                    show.append(D[str(location[0]+ v) + str(location[1])])
                if my_table[location[0] + v][location[1]] in white:
                    show.append(D[str(location[0]+ v) + str(location[1])])
                    break
                if my_table[location[0] + v][location[1]] in black or my_table[location[0] + v][location[1]] == "KI":
                    break
        return show
    if stone == "r1" or stone == "r2":
        find_location(stone)
        x, y, z, v= 0, 0, 0, 0
        show=[]
        while x <= 7:
            x+=1
            if str(location[0])+str(location[1] + x) in D:
                if my_table[location[0]][location[1] + x] == "  ":
                    show.append(D[str(location[0]) + str(location[1] + x)])
                if my_table[location[0]][location[1] + x] in black:
                    show.append(D[str(location[0]) + str(location[1] + x)])
                    break
                if my_table[location[0]][location[1] + x] in white or my_table[location[0]][location[1] + x] == "ki":
                    break

        while y >= -7:
            y-=1
            if str(location[0] )+str(location[1] + y) in D:
                if my_table[location[0]][location[1] + y] == "  ":
                    show.append(D[str(location[0]) + str(location[1] + y)])
                if my_table[location[0]][location[1] + y] in black:
                    show.append(D[str(location[0]) + str(location[1] + y)])
                    break
                if my_table[location[0]][location[1] + y] in white or my_table[location[0]][location[1] + y] == "ki":
                    break

        while z <= 7 :
            z+=1
            if str(location[0] + z)+str(location[1]) in D:
                if my_table[location[0] + z][location[1]] == "  ":
                    show.append(D[str(location[0]+ z) + str(location[1])])
                if my_table[location[0] + z][location[1]] in black:
                    show.append(D[str(location[0]+ z) + str(location[1])])
                    break
                if my_table[location[0] + z][location[1]] in white or my_table[location[0] + z][location[1]] == "ki":
                    break

        while v >= -7:
            v-=1
            if str(location[0] + v)+str(location[1]) in D:
                if my_table[location[0] + v][location[1]] == "  ":
                    show.append(D[str(location[0]+ v) + str(location[1])])
                if my_table[location[0] + v][location[1]] in black:
                    show.append(D[str(location[0]+ v) + str(location[1])])
                    break
                if my_table[location[0] + v][location[1]] in white or my_table[location[0] + v][location[1]] == "ki":
                    break
        return show

    if stone == "qu":
        find_location(stone)
        x, y, z, v, t, x1, y1, z1, v1, t1= 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
        show=[]
        while x <= 7:
            x+=1
            if str(location[0])+str(location[1] + x) in D:
                if my_table[location[0]][location[1] + x] == "  ":
                    show.append(D[str(location[0]) + str(location[1] + x)])
                if my_table[location[0]][location[1] + x] in black:
                    show.append(D[str(location[0]) + str(location[1] + x)])
                    break
                if my_table[location[0]][location[1] + x] in white or my_table[location[0]][location[1] + x] == "ki":
                    break

        while y >= -7:
            y-=1
            if str(location[0] )+str(location[1] + y) in D:
                if my_table[location[0]][location[1] + y] == "  ":
                    show.append(D[str(location[0]) + str(location[1] + y)])
                if my_table[location[0]][location[1] + y] in black:
                    show.append(D[str(location[0]) + str(location[1] + y)])
                    break
                if my_table[location[0]][location[1] + y] in white or my_table[location[0]][location[1] + y] == "ki":
                    break

        while z <= 7 :
            z+=1
            if str(location[0] + z)+str(location[1]) in D:
                if my_table[location[0] + z][location[1]] == "  " :
                    show.append(D[str(location[0]+ z) + str(location[1])])
                if my_table[location[0] + z][location[1]] in black:
                    show.append(D[str(location[0]+ z) + str(location[1])])
                    break
                if my_table[location[0] + z][location[1]] in white or my_table[location[0] + z][location[1]] == "ki":
                    break

        while v >= -7:
            v-=1
            if str(location[0] + v)+str(location[1]) in D:
                if my_table[location[0] + v][location[1]] == "  ":
                    show.append(D[str(location[0]+ v) + str(location[1])])
                if my_table[location[0] + v][location[1]] in black:
                    show.append(D[str(location[0]+ v) + str(location[1])])
                    break
                if my_table[location[0] + v][location[1]] in white or my_table[location[0] + v][location[1]] == "ki":
                    break

        while x1 <= 7 and z1 >= -7:
            z1-=1
            x1+=1
            if str(location[0] + z1)+str(location[1] + x1) in D:
                if my_table[location[0] + z1][location[1] + x1] == "  ":
                    show.append(D[str(location[0] + z1) + str(location[1] + x1)])
                if my_table[location[0] + z1][location[1] + x1] in black:
                    show.append(D[str(location[0] + z1) + str(location[1] + x1)])
                    break
                if my_table[location[0] + z1][location[1] + x1] in white or my_table[location[0] + z1][location[1] + x1] == "ki":
                    break

        while t <= 7 and y1 >= -7:
            t+=1
            y1-=1
            if str(location[0] + t)+str(location[1] + y1) in D:
                if my_table[location[0] + t][location[1] + y1] == "  ":
                    show.append(D[str(location[0] + t) + str(location[1] + y1)])
                if my_table[location[0] + t][location[1] + y1] in black:
                    show.append(D[str(location[0] + t) + str(location[1] + y1)])
                    break
                if my_table[location[0] + t][location[1] + y1] in white or my_table[location[0] + t][location[1] + y1] == "ki":
                    break

        while v1 >= -7 :
            v1-=1
            if str(location[0] + v1)+str(location[1] + v1) in D:
                if my_table[location[0] + v1][location[1] + v1] == "  ":
                    show.append(D[str(location[0] + v1) + str(location[1] + v1)])
                if my_table[location[0] + v1][location[1] + v1] in black:
                    show.append(D[str(location[0] + v1) + str(location[1] + v1)])
                    break
                if my_table[location[0] + v1][location[1] + v1] in white or my_table[location[0] + v1][location[1] + v1] == "ki":
                    break

        while t1 <= 7 :
            t1+=1
            if str(location[0] + t1)+str(location[1] + t1) in D:
                if my_table[location[0] + t1][location[1] + t1] == "  ":
                    show.append(D[str(location[0] + t1) + str(location[1] + t1)])
                if my_table[location[0] + t1][location[1] + t1] in black:
                    show.append(D[str(location[0] + t1) + str(location[1] + t1)])
                    break
                if my_table[location[0] + t1][location[1] + t1] in white or my_table[location[0] + t1][location[1] + t1] == "ki":
                    break
        return show

    if stone == "QU":
        find_location(stone)
        x, y, z, v, t, x1, y1, z1, v1, t1= 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
        show=[]
        while x <= 7:
            x+=1
            if str(location[0])+str(location[1] + x) in D:
                if my_table[location[0]][location[1] + x] == "  ":
                    show.append(D[str(location[0]) + str(location[1] + x)])
                if my_table[location[0]][location[1] + x] in white:
                    show.append(D[str(location[0]) + str(location[1] + x)])
                    break
                if my_table[location[0]][location[1] + x] in black or my_table[location[0]][location[1] + x] == "KI":
                    break

        while y >= -7:
            y-=1
            if str(location[0] )+str(location[1] + y) in D:
                if my_table[location[0]][location[1] + y] == "  ":
                    show.append(D[str(location[0]) + str(location[1] + y)])
                if my_table[location[0]][location[1] + y] in white:
                    show.append(D[str(location[0]) + str(location[1] + y)])
                    break
                if my_table[location[0]][location[1] + y] in black or my_table[location[0]][location[1] + y] == "KI":
                    break

        while z <= 7 :
            z+=1
            if str(location[0] + z)+str(location[1]) in D:
                if my_table[location[0] + z][location[1]] == "  " :
                    show.append(D[str(location[0]+ z) + str(location[1])])
                if my_table[location[0] + z][location[1]] in white:
                    show.append(D[str(location[0]+ z) + str(location[1])])
                    break
                if my_table[location[0] + z][location[1]] in black or my_table[location[0] + z][location[1]] == "KI":
                    break

        while v >= -7:
            v-=1
            if str(location[0] + v)+str(location[1]) in D:
                if my_table[location[0] + v][location[1]] == "  ":
                    show.append(D[str(location[0]+ v) + str(location[1])])
                if my_table[location[0] + v][location[1]] in white:
                    show.append(D[str(location[0]+ v) + str(location[1])])
                    break
                if my_table[location[0] + v][location[1]] in black or my_table[location[0] + v][location[1]] == "KI":
                    break

        while x1 <= 7 and z1 >= -7:
            z1-=1
            x1+=1
            if str(location[0] + z1)+str(location[1] + x1) in D:
                if my_table[location[0] + z1][location[1] + x1] == "  ":
                    show.append(D[str(location[0] + z1) + str(location[1] + x1)])
                if my_table[location[0] + z1][location[1] + x1] in white:
                    show.append(D[str(location[0] + z1) + str(location[1] + x1)])
                    break
                if my_table[location[0] + z1][location[1] + x1] in black or my_table[location[0] + z1][location[1] + x1] == "KI":
                    break

        while t <= 7 and y1 >= -7:
            t+=1
            y1-=1
            if str(location[0] + t)+str(location[1] + y1) in D:
                if my_table[location[0] + t][location[1] + y1] == "  ":
                    show.append(D[str(location[0] + t) + str(location[1] + y1)])
                if my_table[location[0] + t][location[1] + y1] in white:
                    show.append(D[str(location[0] + t) + str(location[1] + y1)])
                    break
                if my_table[location[0] + t][location[1] + y1] in black or my_table[location[0] + t][location[1] + y1] == "KI":
                    break

        while v1 >= -7 :
            v1-=1
            if str(location[0] + v1)+str(location[1] + v1) in D:
                if my_table[location[0] + v1][location[1] + v1] == "  ":
                    show.append(D[str(location[0] + v1) + str(location[1] + v1)])
                if my_table[location[0] + v1][location[1] + v1] in white:
                    show.append(D[str(location[0] + v1) + str(location[1] + v1)])
                    break
                if my_table[location[0] + v1][location[1] + v1] in black or my_table[location[0] + v1][location[1] + v1] == "KI":
                    break

        while t1 <= 7 :
            t1+=1
            if str(location[0] + t1)+str(location[1] + t1) in D:
                if my_table[location[0] + t1][location[1] + t1] == "  ":
                    show.append(D[str(location[0] + t1) + str(location[1] + t1)])
                if my_table[location[0] + t1][location[1] + t1] in white:
                    show.append(D[str(location[0] + t1) + str(location[1] + t1)])
                    break
                if my_table[location[0] + t1][location[1] + t1] in black or my_table[location[0] + t1][location[1] + t1] == "KI":
                    break
        return show

    if stone == "KI":
        find_location(stone)
        show=[]
        if str(location[0]-1)+str(location[1]) in D:
            if my_table[location[0]-1][location[1]] == "  " or my_table[location[0]-1][location[1]] in white:
                show.append(D[str(location[0]-1)+str(location[1])])
            else:
                pass
        if str(location[0]+1)+str(location[1]) in D:
            if my_table[location[0]+1][location[1]] == "  " or my_table[location[0]+1][location[1]] in white:
                show.append(D[str(location[0]+1)+str(location[1])])
            else:
                pass
        if str(location[0]+1)+str(location[1]+1) in D:
            if my_table[location[0]+1][location[1]+1] == "  " or my_table[location[0]+1][location[1]+1] in white:
                show.append(D[str(location[0]+1)+str(location[1]+1)])
            else:
                pass
        if str(location[0]+1)+str(location[1]-1) in D:
            if my_table[location[0]+1][location[1]-1] == "  " or my_table[location[0]+1][location[1]-1] in white:
                show.append(D[str(location[0]+1)+str(location[1]-1)])
            else:
                pass
        if str(location[0]-1)+str(location[1]+1) in D:
            if my_table[location[0]-1][location[1]+1] == "  " or my_table[location[0]-1][location[1]+1] in white:
                show.append(D[str(location[0]-1)+str(location[1]+1)])
            else:
                pass
        if str(location[0]-1)+str(location[1]-1) in D:
            if my_table[location[0]-1][location[1]-1] == "  " or my_table[location[0]-1][location[1]-1] in white:
                show.append(D[str(location[0]-1)+str(location[1]-1)])
            else:
                pass
        if str(location[0])+str(location[1]+1) in D:
            if my_table[location[0]][location[1]+1] == "  " or my_table[location[0]][location[1]+1] in white:
                show.append(D[str(location[0])+str(location[1]+1)])
            else:
                pass
        if str(location[0])+str(location[1]-1) in D:
            if my_table[location[0]][location[1]-1] == "  " or my_table[location[0]][location[1]-1] in white:
                show.append(D[str(location[0])+str(location[1]-1)])
            else:
                pass
        return show
    if stone == "ki":
        find_location(stone)
        show=[]
        if str(location[0]-1)+str(location[1]) in D:
            if my_table[location[0]-1][location[1]] == "  " or my_table[location[0]-1][location[1]] in black:
                show.append(D[str(location[0]-1)+str(location[1])])
            else:
                pass
        if str(location[0]+1)+str(location[1]) in D:
            if my_table[location[0]+1][location[1]] == "  " or my_table[location[0]+1][location[1]] in black:
                show.append(D[str(location[0]+1)+str(location[1])])
            else:
                pass
        if str(location[0]+1)+str(location[1]+1) in D:
            if my_table[location[0]+1][location[1]+1] == "  " or my_table[location[0]+1][location[1]+1] in black:
                show.append(D[str(location[0]+1)+str(location[1]+1)])
            else:
                pass
        if str(location[0]+1)+str(location[1]-1) in D:
            if my_table[location[0]+1][location[1]-1] == "  " or my_table[location[0]+1][location[1]-1] in black:
                show.append(D[str(location[0]+1)+str(location[1]-1)])
            else:
                pass
        if str(location[0]-1)+str(location[1]+1) in D:
            if my_table[location[0]-1][location[1]+1] == "  " or my_table[location[0]-1][location[1]+1] in black:
                show.append(D[str(location[0]-1)+str(location[1]+1)])
            else:
                pass
        if str(location[0]-1)+str(location[1]-1) in D:
            if my_table[location[0]-1][location[1]-1] == "  " or my_table[location[0]-1][location[1]-1] in black:
                show.append(D[str(location[0]-1)+str(location[1]-1)])
            else:
                pass
        if str(location[0])+str(location[1]+1) in D:
            if my_table[location[0]][location[1]+1] == "  " or my_table[location[0]][location[1]+1] in black:
                show.append(D[str(location[0])+str(location[1]+1)])
            else:
                pass
        if str(location[0])+str(location[1]-1) in D:
            if my_table[location[0]][location[1]-1] == "  " or my_table[location[0]][location[1]-1] in black:
                show.append(D[str(location[0])+str(location[1]-1)])
            else:
                pass
        return show

for first in commands:
    for second in first:
        if len(second) == 3:
            print(">",*second)
            showmoves(second[1])
            if second[2] in show:
                find_location(second[1])
                my_table[H[second[2]][0]][H[second[2]][1]]=second[1]
                my_table[location[0]][location[1]] = "  "
                print("OK")
            else:
                print("FAILED")
        if len(second) == 2:
            print(">",*second)
            showmoves(second[1])
            if len(show) == 0:
                print("FAILED")
            else:
                print(*sorted(show))
        if len(second) == 1:
            if second[0] == "print":
                print(">","print")
                print("-----------------------")
                for i in my_table:
                    for j in i:
                        print(j,end=" ")
                    print(end="\n")
                print("-----------------------")
            elif second[0] == "initialize":
                initialize()
            elif second[0] == "exit":
                print(">","exit")
                exit()