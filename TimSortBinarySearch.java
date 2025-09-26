import java.util.Scanner;
public class TimSortBinarySearch {
    
    public static void timSort(int[] numbers) {
        int size = numbers.length;
        int minRun = getMinRun(size);
        
        for (int start = 0; start < size; start += minRun) {
            int end = Math.min(start + minRun - 1, size - 1);
            insertionSort(numbers, start, end);
        }
        
        for (int groupSize = minRun; groupSize < size; groupSize = groupSize * 2) {
            for (int left = 0; left < size; left += groupSize * 2) {
                int middle = left + groupSize - 1;
                int right = Math.min(left + groupSize * 2 - 1, size - 1);
                if (middle < right) {
                    merge(numbers, left, middle, right);
                }
            }
        }
    }
    
    private static int getMinRun(int size) {
        int r = 0;
        while (size >= 64) {
            r |= size & 1;
            size >>= 1;
        }
        return size + r;
    }
    
    private static void insertionSort(int[] numbers, int start, int end) {
        for (int i = start + 1; i <= end; i++) {
            int current = numbers[i];
            int j = i - 1;
            while (j >= start && numbers[j] > current) {
                numbers[j + 1] = numbers[j];
                j--;
            }
            numbers[j + 1] = current;
        }
    }
    
    private static void merge(int[] numbers, int left, int middle, int right) {
        int leftSize = middle - left + 1;
        int rightSize = right - middle;
        
        int[] leftGroup = new int[leftSize];
        int[] rightGroup = new int[rightSize];
        
        for (int i = 0; i < leftSize; i++) {
            leftGroup[i] = numbers[left + i];
        }
        for (int j = 0; j < rightSize; j++) {
            rightGroup[j] = numbers[middle + 1 + j];
        }
        
        int i = 0, j = 0, k = left;
        
        while (i < leftSize && j < rightSize) {
            if (leftGroup[i] <= rightGroup[j]) {
                numbers[k] = leftGroup[i];
                i++;
            } else {
                numbers[k] = rightGroup[j];
                j++;
            }
            k++;
        }
        
        while (i < leftSize) {
            numbers[k] = leftGroup[i];
            i++;
            k++;
        }
        
        while (j < rightSize) {
            numbers[k] = rightGroup[j];
            j++;
            k++;
        }
    }
    
    public static int binarySearch(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        
        while (left <= right) {
            int middle = left + (right - left) / 2;
            
            if (numbers[middle] == target) {
                return middle;
            } else if (numbers[middle] < target) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        
        return -1;
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.print("How many numbers? ");
        int count = input.nextInt();
        
        int[] numbers = new int[count];
        System.out.print("Enter " + count + " numbers: ");
        for (int i = 0; i < count; i++) {
            numbers[i] = input.nextInt();
        }
        
        System.out.print("Original: ");
        for (int i = 0; i < count; i++) {
            System.out.print(numbers[i] + " ");
        }
        System.out.println();
        
        timSort(numbers);
        
        System.out.print("Sorted: ");
        for (int i = 0; i < count; i++) {
            System.out.print(numbers[i] + " ");
        }
        System.out.println();
        
        System.out.print("Search for a number? (Y/N): ");
        String answer = input.next();
        
        if (answer.equalsIgnoreCase("Y")) {
            System.out.print("Which number to find? ");
            int findThis = input.nextInt();
            
            int position = binarySearch(numbers, findThis);
            
            if (position != -1) {
                System.out.println("Found " + findThis + " at position " + position);
            } else {
                System.out.println(findThis + " not found");
            }
        }
        
        input.close();
    }
}