/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;
/**\par
 *\par
 * @author Schramm\par
 */
public class Coordinate {
    
    private int column;
    private int row;
    
    public Coordinate(int column, int row) throws IndexOutOfBoundsException
    {
        if ( (column<0)|| (column>7)) throw new IndexOutOfBoundsException("column must be between 0 and 7,inclusive");
        if ( (row<0)|| (row>7)) throw new IndexOutOfBoundsException("row must be between 0 and 7,inclusive");
        this.column = column;
        this.row = row;
        
    }
    
    public Coordinate(char column, char row) throws IndexOutOfBoundsException
    {
        if ( (column<'a')|| (column>'h')) throw new IndexOutOfBoundsException("column must be between a and h,inclusive");
        if ( (row<'1')|| (row>'8')) throw new IndexOutOfBoundsException("row must be between 1 and 8,inclusive");
        this.column = (int)(column - 97);
        this.row = (int)(row - 49);
        
    }   
    
    public Coordinate(String coordinate) throws IndexOutOfBoundsException
    {
        if (coordinate.length() != 2) throw new IllegalArgumentException ("Coordinate is a 2-character string");
        char column = coordinate.charAt(0);
        char row = coordinate.charAt(1);
        // this(x,y) except it must be the first statement!
        if ( (column<'a')|| (column>'h')) throw new IndexOutOfBoundsException("x must be between a and h, inclusive");
        if ( (row<'1')|| (row>'8')) throw new IndexOutOfBoundsException("y must be between 1 and 8, inclusive");
        this.column = (int)(column - 97);
        this.row = (int)(row - 49);
        
    }  
    
   public char getColumn() { return (char)(this.column + 97);}
    
   public int getColumnNumber(){ return this.column; }
   
   public char getRow() { return (char)(this.row + 49);}
    
   public int getRowNumber(){ return this.row; }
   
   public String name() {return (Character.toString((char)(this.column + 97)) + Character.toString((char)(this.row + 49)));}
   
   public String toString() { return  ""  + (char)(this.column + 48) +(char)(this.row + 48); }
   

}
           

