import sys
import os
class ParameterNumberError(Exception):
    pass
class UndefinedParameterError(Exception):
    pass
class InputFileNotFoundError(Exception):
    pass
class InputFilecouldntReadError(Exception):
    pass
class InputFileEmptyError(Exception):
    pass
class InvalidChaError(Exception):
    pass
class KeyFileNotFoundError(Exception):
    pass
class KeyFileCouldntReadError(Exception):
    pass
class KeyFileEmptyError(Exception):
    pass
class InvalidChaInKeyError(Exception):
    pass


try:
    if len(sys.argv) != 5:
        raise ParameterNumberError
    elif sys.argv[1] != "enc" and sys.argv[1] != "dec":
        raise UndefinedParameterError
    elif not os.path.exists((sys.argv[3])):
        raise FileNotFoundError
    elif not (sys.argv[3]).endswith(".txt"):
        raise InputFilecouldntReadError
    elif not os.path.exists((sys.argv[2])):
        raise KeyFileNotFoundError
    elif not (sys.argv[2]).endswith(".txt"):
        raise KeyFileCouldntReadError
except ParameterNumberError:
    print("Parameter number error")
    exit()
except UndefinedParameterError:
    print("Undefined parameter error")
    exit()
except FileNotFoundError:
    print("Input file not found error")
    exit()
except InputFilecouldntReadError:
    print("The input file could not be read error")
    exit()
except KeyFileNotFoundError:
    print("Key file not found error")
    exit()
except KeyFileCouldntReadError:
    print("Key file could not be read")
    exit()


def multiplication(key_matrix, b):      # matrix multiplication
    global ase
    c, ase = [], []
    e, o = 0, len(key_matrix)
    while e < len(b):
        result = []
        for i in range(len(key_matrix)):
            result.append(0)
        result = [result[j:j+1] for j in range(0, len(key_matrix), 1)]
        c = b[e:o]
        for i in range(len(key_matrix)):
            for j in range(1):
                for k in range(len(c)):
                    result[i][j] += key_matrix[i][k] * c[k][j]
        ase += result
        e += len(key_matrix)
        o += len(key_matrix)
    return ase


def inverse2x2(key_two):    # finding 2x2 inverse matrix
    global inverse_key2
    determinant = 1/((key_two[0][0] * key_two[1][1]) - key_two[0][1] * key_two[1][0])
    key_two[0][0], key_two[0][1], key_two[1][0], key_two[1][1] = key_two[1][1], -key_two[0][1], -key_two[1][0], key_two[0][0]
    inverse_key = []
    for i in key_two:
        for j in i:
            inverse_key.append(int(determinant*j))
    inverse_key2 = [inverse_key[j:j+2] for j in range(0, len(inverse_key),2)]
    return inverse_key2


def inverse3x3(key_three):      # finding 3x3 inverse matrix
    global inverse_key3         # first find determinant with using Sarrus method
    determinant = 1 / (((key_three[0][0] * key_three[1][1] * key_three[2][2]) + (key_three[1][0] * key_three[2][1] * key_three[0][2]) + (key_three[2][0] * key_three[0][1] * key_three[1][2])) - ((key_three[0][2] * key_three[1][1] * key_three[2][0]) + (key_three[1][2] * key_three[2][1] * key_three[0][0]) + (key_three[2][2] * key_three[0][1] * key_three[1][0])))
    a1 = (key_three[1][1] * key_three[2][2]) - (key_three[1][2] * key_three[2][1])
    b1 = -((key_three[1][0] * key_three[2][2]) - (key_three[1][2] * key_three[2][0]))
    c1 = (key_three[1][0] * key_three[2][1]) - (key_three[1][1] * key_three[2][0])
    a2 = -((key_three[0][1] * key_three[2][2]) - (key_three[0][2] * key_three[2][1]))
    b2 = (key_three[0][0] * key_three[2][2]) - (key_three[0][2] * key_three[2][0])
    c2 = -((key_three[0][0] * key_three[2][1]) - (key_three[0][1] * key_three[2][0]))
    a3 = (key_three[0][1] * key_three[1][2]) - (key_three[0][2] * key_three[1][1])
    b3 = -((key_three[0][0] * key_three[1][2]) - (key_three[0][2] * key_three[1][0]))
    c3 = (key_three[0][0] * key_three[1][1]) - (key_three[0][1] * key_three[1][0])
    key_three[0][0] = a1
    key_three[1][0] = b1
    key_three[2][0] = c1
    key_three[0][1] = a2
    key_three[1][1] = b2
    key_three[2][1] = c2
    key_three[0][2] = a3
    key_three[1][2] = b3
    key_three[2][2] = c3
    inverse_key = []   # than multiplying all elements of the matrix with determinant
    for i in key_three:
        for j in i:
            inverse_key.append(int(determinant*j))
    inverse_key3 = [inverse_key[j:j+3] for j in range(0, len(inverse_key), 3)]
    return inverse_key3


D = {"A": 1, "B": 2, "C": 3, "D": 4, "E": 5, "F": 6, "G": 7, "H": 8, "I": 9, "J": 10,               # Letter to number Dictionary
     "K": 11, "L": 12, "M": 13, "N": 14, "O": 15, "P": 16, "Q": 17, "R": 18, "S": 19, "T": 20,
     "U": 21, "V": 22, "W": 23, "X": 24, "Y": 25, "Z": 26, " ": 27,
     "a": 1, "b": 2, "c": 3, "d": 4, "e": 5, "f": 6, "g": 7, "h": 8, "i": 9, "j": 10,
     "k": 11, "l": 12, "m": 13, "n": 14, "o": 15, "p": 16, "q": 17, "r": 18, "s": 19, "t": 20,
     "u": 21, "v": 22, "w": 23, "x": 24, "y": 25, "z": 26}
N = {y: x for x, y in D.items()}                                                                    # Number to letter Dictionary
try:
    key = []
    with open(sys.argv[2]) as f:                                                                    # reaching key matrix
        array = [[x for x in line.split()] for line in f]
    if len(array) == 0:
        raise KeyFileEmptyError
    for i in array:
        for j in i:
            key.append([int(n) for n in j.split(',')])
    if sys.argv[1] == "enc":                                                                        # encoding the message
        with open(sys.argv[3], "r+") as t:                                                          # first reading message
            a = t.readline().replace("\n", "")
        if len(a) == 0:
            raise InputFileEmptyError
        b = []
        for first in a:                                                                             # than convert it to integer form
            if first not in D:
                raise InvalidChaError
            else:
                b.append([D[first]])
        while len(b) % len(key) != 0:                                                               # adding 27 for spaces
            b.append([27])
        multiplication(key, b)                                                                      # matrix multiplication
        last = str(ase).replace("[", "").replace("]", "").replace(" ", "")                          # making proper for writing to the output file
        with open(sys.argv[4], "w+") as o:
            o.write(last)
    elif sys.argv[1] == "dec":
        if len(key) == 2:
            inverse2x2(key)                                                         # finding 2x2 inverse matrix
            b = []
            un = []
            with open(sys.argv[3], "r+") as r:                                      # first reaching cipher text
                array = [[x for x in line.split()] for line in r]
                for i in array:
                    for j in i:
                        un.append([int(n) for n in j.split(',')])                   # making proper for matrix multiplication
                for first in un:
                    for second in first:
                        b.append([second])
            multiplication(inverse_key2, b)                                         # matrix multiplication
            last = ""                                                               # finding plain text
            for first in ase:
                for second in first:
                    last += N[second]
            with open(sys.argv[4], "w+") as m:
                m.write(last)
        elif len(key) == 3:                                                         # finding 3x3 inverse matrix
            inverse3x3(key)
            un = []
            b = []
            with open(sys.argv[3], "r+") as r:
                array = [[x for x in line.split()] for line in r]
                for i in array:
                    for j in i:
                        un.append([int(n) for n in j.split(',')])                   # making proper for matrix multiplication
                for first in un:
                    for second in first:
                        b.append([second])
            multiplication(inverse_key3, b)                                         # matrix multiplication
            last = ""                                                               # finding plain text
            for first in ase:
                for second in first:
                    last += N[second]
            with open(sys.argv[4], "w+") as m:
                m.write(last)
except InputFileEmptyError:
    print("Input file is empty error")
    exit()
except InvalidChaError:
    print("Invalid character in input file error")
    exit()
except KeyFileEmptyError:
    print("Key file is empty error")
    exit()
except ValueError:
    print("Invalid character in key file error")
    exit()