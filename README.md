# Decorator Design Pattern

## Definition

Decorator is a structural design pattern that lets you attach new behaviors to objects by placing these objects inside special wrapper objects that contain the behaviors.

![img_1.png](src/img_1.png)

## Example
### Encoding and Compression with user of Decorator pattern

The start state is that we have FileDataSource that takes a String and saves it to file or load it back from file to String.
Solution shows how to implement additional steps before saving file, that is -> encrypt -> compress. And back in case of reading the file.
We use Decorator pattern to achieve that goal.

```text
- Input ----------------
Name,Salary
John Smith,100000
Steven Jobs,912000
- Encoded --------------
Zkt7e1Q5eU8yUm1Qe0ZsdHJ2VXp6dDBKVnhrUHtUe0sxRUYxQkJIdjVLTVZ0dVI5Q2IwOXFISmVUMU5rcENCQmdxRlByaD4+
- Decoded --------------
Name,Salary
John Smith,100000
Steven Jobs,912000
```

![img.png](src/img.png)

## Other

- Example is from awesome website [Refactoring Guru](https://refactoring.guru)