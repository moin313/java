package practice_quetions;

public class MergeSort {
    public static void main(String[] args) {
        int arr[] = {1,4,7,9,11,32}, arr1[] = {5,8,10,13,14}, mergedArray[] = new int[(arr.length+arr1.length)];
        int i=0, j=0, k=0;
        while(true){
            if( arr1.length > j  && arr[i] > arr1[j]){
               mergedArray[k] = arr1[j];
               k++;
               j++;
            }else if(arr.length > i){
                mergedArray[k] = arr[i];
                k++;
                i++;
            }
            else
                break;
        }
        for (int el : mergedArray) {
            System.out.print(el+" ");
        }
    }
}
