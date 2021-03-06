/***********************************************************************

	This file is part of KEEL-software, the Data Mining tool for regression, 
	classification, clustering, pattern mining and so on.

	Copyright (C) 2004-2010
	
	F. Herrera (herrera@decsai.ugr.es)
    L. Sánchez (luciano@uniovi.es)
    J. Alcalá-Fdez (jalcala@decsai.ugr.es)
    S. García (sglopez@ujaen.es)
    A. Fernández (alberto.fernandez@ujaen.es)
    J. Luengo (julianlm@decsai.ugr.es)

	This program is free software: you can redistribute it and/or modify
	it under the terms of the GNU General Public License as published by
	the Free Software Foundation, either version 3 of the License, or
	(at your option) any later version.

	This program is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU General Public License for more details.

	You should have received a copy of the GNU General Public License
	along with this program.  If not, see http://www.gnu.org/licenses/
  
**********************************************************************/

/**
 * <p>
 * @author Written by Alberto Fernández (University of Granada)  01/07/2008
 * @author Modified by Xavi Solé (La Salle, Ramón Llull University - Barcelona) 03/12/2008
 * @version 1.1
 * @since JDK1.2
 * </p>
 */



package keel.Algorithms.Rule_Learning.Slipper;

import org.core.*;
/**
 * Representation of a mask over a MyDataset.
 * It allows to select a given set of entries without changing the MyDataset.
 * In fact, it acts as a multiplexer over the MyDataset's entries.
 * It also provides a cursor over those elements.
 */
public class Mask {
  
  //this vector indicates the active entries of the dataset
  private boolean[] mask; 
  //number of active entries
  private int nactivos; 
  //cursor, always indicates an active entry
  private int index; 

  /**
   * Constructs a Mask of a given length. By default, it actives all entries.
   * The cursor is set atBegin (that's a non valid position and it will be necessary a next() to reach the first active position).
   * @param size int the Mask's length
   */
  public Mask(int size) {
    mask=new boolean[size];
    nactivos=size;
    for(int i=0;i<size;i++)
      mask[i]=true;
    index=-1;
  }

  /**
   * Constructs a Mask of a given length
   * The cursor is set atBegin (that's a non valid position and it will be necessary a next() to reach  the first active position).
   * @param size int the Mask's length
   * @param initial boolean initial value of the all entries
   */
  public Mask(int size,boolean initial) {
    mask=new boolean[size];
    nactivos=size;
    for(int i=0;i<size;i++)
      mask[i]=initial;
    index=-1;
  }

  /* private constructor */
  private Mask(boolean[] mask,int nactivos) {
    this.mask=mask;
    this.nactivos=nactivos;
    index=-1;
  }

  /**
   * Returns a copy of this Mask
   * @return a copy of this Mask
   */
  public Mask copy(){
    boolean[] replicate=new boolean[mask.length];
    for (int i=0;i<mask.length;i++)
      replicate[i]=mask[i];
  return new Mask(replicate,nactivos);
  }

  /**
   * Copies this Mask into another Mask
   * @param replicate Mask a future copy of this Mask
   */
  public void copyTo(Mask replicate){
    for (int i=0;i<this.mask.length;i++)
      replicate.mask[i]=this.mask[i];
    replicate.nactivos=this.nactivos;
    replicate.index=-1;
  }

  /**
   * Returns wether a given entry is active or not
   * @param i int the index of the entry
   * @return true if a given entry is active
   */
  public boolean isActive(int i){return mask[i];}

  /**
   * Deactivates the entry pointed by the cursor
   */
  public void reset(){
    if (mask[index])
      nactivos--;
    mask[index]=false;
  }

  /**
   * Changes the value of a given position (if it was activated, it is now deactivated and vicecersa).
   * @param i int number of entry to change
   */
  public void change(int i){
    mask[i]=!mask[i];
    if (mask[i])
      nactivos++;
    else
      nactivos--;
  }

  /**
   * Activates the value of a given position.
   * @param i int number of entry to active
   */
  public void set(int i){
    if (!mask[i])
      nactivos++;
    mask[i]=true;
  }

  /**
   * Deactivates the value of a given position.
   * @param i int number of entry to deactive
   */
  public void reset(int i){
    if (mask[i])
      nactivos--;
    mask[i]=false;
  }


  /**
   * Sets the states (activated or deactivated) of a given position.
   * @param i int number of entry to set
   * @param value boolean new value for the entry
   */
  public void set(int i,boolean value){
    if (mask[i])
      nactivos--;

    mask[i]=value;

    if (mask[i])
      nactivos++;
  }

  /**
   * Returns a complementary mask of this mask
   * (the activated entries of this mask are deactivated and viceversa).
   * @return a complementary mask of this mask.
   */
  public Mask complement(){
    Mask comp=new Mask(mask.length);
    for (int i=0;i<mask.length;i++)
      comp.mask[i]=!mask[i];
    comp.nactivos=mask.length-nactivos;
    return comp;
  }

  /**
   * Returns the mask that it's the outcome of the bolean operation 'and' between this and a given mask.
   * @param m Mask the other mask
   * @return (this & m)
   */
  public Mask and(Mask m){
    Mask output=new Mask(this.mask.length);
    for (int i=0;i<this.mask.length;i++){
      output.mask[i] = (this.mask[i] && m.mask[i]);
      if (!output.mask[i])
        output.nactivos--;
    }
    return output;
  }

  /**
   * Returns the mask that it's the outcome of the bolean operation 'or' between this and a given mask.
   * @param m Mask the other mask
   * @return (this | m)
   */
  public Mask or(Mask m){
    Mask output=new Mask(this.mask.length);
    for (int i=0;i<this.mask.length;i++){
      output.mask[i] = (this.mask[i] || m.mask[i]);
      if (!output.mask[i])
        output.nactivos--;
    }
    return output;
  }

  /**
   * Return the number of active entries
   * @return the number of active entries
   */
  public int getnActive(){
    return nactivos;
  }

  /**
   * Advances the cursor to the next active entry.
   * If the cursor reach the end of the mask, it returns false.
   * @return false if the cursor has reached the end of the mask.
   */
  public boolean next(){
    do{
      index++;
    }while (index<mask.length && !mask[index]);
    return index<mask.length;
  }

  /**
   * Returns the position pointed by the cursors.
   * @return the position pointed by the cursors.
   */
  public int getIndex(){
    return index;
  }

  /**
   * Sets the cursor at atBegin
   * (that's a non valid position and it will be necessary a next() to reach the first active position).
   */
  public void resetIndex(){
    index=-1;
  }

  /**
   * Splits, at random, this mask in two new masks
   * @param pct double spliting ratio, the length of the first outcome mask will be pct*|this|
   * @param rand Randomize random number generator
   * @return two masks ([0] || [1] = this) (the [0] has a length: (pct*|this|).
   */
  public Mask[] split(double pct,Randomize rand){
    boolean[] half1=new boolean[mask.length];
    boolean[] half2=new boolean[mask.length];
    int[] availables=new int[mask.length];
    //initialization of the output masks
    for (int j=0;j<mask.length;j++){
      half1[j] = false;
      half2[j] = false;
    }
    //tuning up of the availables vector
    int i=0;
    resetIndex();
    while(next()){
      availables[i]=getIndex();
      i++;
      half2[getIndex()]=true;
    }
    //extraction of the active positions
    for (int j=0;j<Math.floor(pct*nactivos);j++){
      int num=rand.Randint(0,i-j);
      half1[availables[num]]=true;
      half2[availables[num]]=false;
      availables[num]=availables[i-j-1];
    }
    //preparation of the output
    Mask output1=new Mask(half1,(int)Math.floor(pct*nactivos));
    Mask output2=new Mask(half2,nactivos-output1.getnActive());
    Mask[] outputv=new Mask[2];
    outputv[0]=output1;
    outputv[1]=output2;
    return outputv;
  }

}

