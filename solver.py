import numpy as np


N = 9

# example board
board = np.array([
    [7,8,0,4,0,0,1,2,0],
    [6,0,0,0,7,5,0,0,9],
    [0,0,0,6,0,1,0,7,8],
    [0,0,7,0,4,0,2,6,0],
    [0,0,1,0,5,0,9,3,0],
    [9,0,4,0,6,0,0,0,5],
    [0,7,0,3,0,0,0,1,2],
    [1,2,0,0,0,7,4,0,0],
    [0,4,9,2,0,6,0,0,7]
])


def print_board(board):
    for i in range(N):
        for j in range(N):
            if j % 3 == 0 and j != 0:
                print("| " + str(board[i][j]), end=" ")
            else:
                print(board[i][j], end=" ")
        if i % 3 == 2 and i != N-1:
            print("\n--------------------")
        else:
            print()


def next_empty_space(board):
    for i in range(N):
        for j in range(N):
            if board[i][j] == 0:
                return i,j
    return -1


def check_valid(board, num, pos):
    # check if row is valid
    if (board[pos[0]] == num).sum() > 0:
        return False

    # check if column is valid
    if (board[:, pos[1]] == num).sum() > 0:
        return False

    # check subgrid
    box_x = pos[1] // 3
    box_y = pos[0] // 3
    box = board[box_y*3:box_y*3+3, box_x*3:box_x*3+3]
    if (box == num).sum() > 0:
        return False

    return True


def solve(board):

    empty = next_empty_space(board)
    if empty == -1:
        return True

    row, col = empty

    for i in range(1,N+1):

        if check_valid(board, i, (row, col)):
            board[row][col] = i

            if solve(board):
                return True

            board[row][col] = 0

    return False





print_board(board)
solve(board)
print("\n\n\n")
print_board(board)
