# Chess Game
Стандартная реализация популярной игры Шахматы

## Description
Игра написана на языке программирования Java с использованием библиотеки Swing GUI.
Играть можно как против самого себя, так и против компьютера. Искусственный интеллект противника 
реализован при помощи алгоритма Minimax. Для ускорения обработки дерева возможных ходов используется 
альфа-бета отсечение. Таким образом, компьютер обрабатывает около 200.000 возможных ходов перед тем 
как принять оптимальное решение. Для оценки текущей ситуации используется функция оценки, основанная 
на [статье](https://www.freecodecamp.org/news/simple-chess-ai-step-by-step-1d55a9266977/)

![values](https://cdn-media-1.freecodecamp.org/images/1*e4p9BrCzJUdlqx7KVGW9aA.png)
![table](https://cdn-media-1.freecodecamp.org/images/1*iG6FUYZpU0_RKlqHnC8XxA.png)
## How to play
В папке PROJECT есть .jar файл, нужна java 1.8

## Preview

![animation](https://s7.gifyu.com/images/ezgif.com-gif-makera296280df482abc5.gif)