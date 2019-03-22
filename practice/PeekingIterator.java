import java.util.NoSuchElementException;

/*
    Example:

    Assume that the iterator is initialized to the beginning of the list: [1,2,3].

    Call next() gets you 1, the first element in the list.
    Now you call peek() and it returns 2, the next element. Calling next() after that still return 2. 
    You call next() the final time and it returns 3, the last element. 
    Calling hasNext() after that should return false.
*/
class PeekingIterator implements Iterator<Integer> {

    private Iterator<Integer> iter;
    private Integer peekVal;
    
    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        iter = iterator;
        peekVal = null;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if(!hasNext()){
            throw new NoSuchElementException();
        }
        if(peekVal != null){
            return peekVal;            
        }
        peekVal = iter.next();
        return peekVal;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        if(!hasNext()){
            throw new NoSuchElementException();
        }
        if(peekVal != null){
            Integer tmp = peekVal;
            peekVal = null;
            return tmp;
        }
        return iter.next();
    }

    @Override
    public boolean hasNext() {
        return (iter.hasNext() || peekVal != null);
    }
}