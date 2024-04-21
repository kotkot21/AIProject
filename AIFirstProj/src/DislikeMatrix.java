public class DislikeMatrix {
    private double[][] matrix;
    public DislikeMatrix(double[][] matrix) {
        this.matrix = matrix;
    }
    public double getDislike(String person1, String person2) {
        int index1 = getIndex(person1);
        int index2 = getIndex(person2);
        if (index1 == -1 || index2 == -1) {
            return -1;
        }
        return matrix[index1][index2];
    }

    private int getIndex(String person) {
        String[] names = {"Ahmed", "Salem", "Ayman", "Hani", "Kamal", "Samir", "Hakam", "Fuad", "Ibrahim", "Khalid"};
        for (int i = 0; i < names.length; i++) {
            if (names[i].equals(person)) {
                return i;
            }
        }
        return -1;
    }


}
