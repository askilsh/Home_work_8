public class BoxUtil {
    private static Box<Fruit> src2Box = new Box<>();
    private static Box<Object> srcBox = new Box<>();
    private static Box<Object> destBox = new Box<>();
    private static Box<Object> dest2Box = new Box<>();
    private static Box<Object> dest3Box = new Box<>();

    private static Apple apple = new Apple();
    private static Apple badApple = new Apple();
    private static Fruit fruit = new Fruit();

    /*public static void main(final String[] argc) {
        Box<Apple> srcBox = new Box<>();
        Apple apple = new Apple();
        apple.setFresh(false);
        srcBox.putToBox(apple);
        Box<Object> destBox = new Box<>();
        BoxUtil.copyFreshFruitFromBoxToBox(srcBox, destBox);
        System.out.println(destBox.getFromBox());
        //console null


    }*/

    public static <T> void copyElementToBox(final Box<T> src, final Box<? super T> dest) {
        dest.putToBox(src.getFromBox());
    }

    public static <T extends Fruit> void copyFreshFruitFromBoxToBox(final Box<T> src, final Box<? super T> dest) {
        try {

            if (src.getFromBox().getFresh()) {
                dest.putToBox(src.getFromBox());
            }
        } catch (NullPointerException e) {
            System.out.println("В коробке нет фрукта.");
        }
    }

    public static <T> void printElementFromTwoBoxes(final Box<T> src) {
        T object;
        Box<T> puff;
        object = src.getFromBox();
        try {
            puff = (Box<T>) object;
        } catch (ClassCastException e) {
            System.out.println("В первой коробке лежит не коробка. <" + e + ">");
            return;
        }
        try {
            System.out.println("Во второй коробке лежит - " + puff.getFromBox());
        } catch (NullPointerException er) {
            System.out.println("В первой коробке ничего нет. <" + er + ">");
        }
    }

    public static <T> void printElementFromBoxes(final Box<? extends T> box) {
        boolean key = true;
        T object;
        Box<T> puff;

        object = box.getFromBox();
        while (key) {
            if (object instanceof Box) {
                puff = (Box<T>) object;
                object = puff.getFromBox();
            } else {
                System.out.println("В последней коробке находится - " + object);
                key = false;
            }
        }
    }
    /* Это такой же 4-й метод, только работает через try catch, который ловит объект последней коробки как исключение.
    Особенность в том, что spotbags ругается на бесконечную рекурсию, хоть ее и нет. Но если сделать условие, в которое
    передать bool, а ей присвоть true, то бага больше нет (если просто написать в условии true, то ругается)
     */
    /*public static <T> void printElementFromBoxesRecursive(final Box<? extends T> box) {
        T object;
        boolean key = true;
        Box<T> puff;
        object = box.getFromBox();
        try {
            puff = (Box<T>) object;
            //если здесь в условии написать true то будет спотбагз покажет баг
            if (key) {
                printElementFromBoxes(puff);
            }
        } catch (ClassCastException e) {
            System.out.println("В последней коробке лежит - " + object);
        } catch (NullPointerException x) {
            System.out.println("Ящик пустой. Объекта в нем нет. " + x);
        }
    }*/
}
