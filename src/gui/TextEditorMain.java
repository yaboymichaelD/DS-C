package gui;

import javax.swing.JFrame;

import spelling.*;

public class TextEditorMain
{  
   public static void main(String[] args)
   {
      Dictionary dictionary = new DictionaryArrayList("data/words_alpha.txt");
      //Dictionary dictionary = new DictionaryLinkedList("data/words_alpha.txt");
      //Dictionary dictionary = new DictionaryAVLTree("data/words_alpha.txt");
      //Dictionary dictionary = new DictionaryTreeSet("data/words_alpha.txt");

      JFrame frame = new TextEditorFrame(dictionary);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
   }
}
