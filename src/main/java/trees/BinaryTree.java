package trees;

public class BinaryTree<T> implements BinaryTreeInterface<T>
{
   private BinaryNode<T> root;

   public BinaryTree()
   {
      root = null;
   } // end default constructor

   public BinaryTree(T rootData)
   {
      root = new BinaryNode<>(rootData);
   } // end constructor

   public BinaryTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree)
   {
      initializeTree(rootData, leftTree, rightTree);
   } // end constructor

   //extra feature
   public BinaryTree(String pre, String in)
   {
      System.out.println(pre + " and " + in);
       /*
        Given Tree:
             A
           /   \
          B     C
         / \   /
        D   E  F
                \
                 G
        */

      // pre : ABDECFG
      // in : DBEAFGC

      // If it's just one node or not
      if(!(pre.length() <= 1 || in.length() <= 1))
      {
         String root = pre.substring(0, 1);
         String inLeftSubtree = "";
         String inRightSubtree = "";
         String preLeftSubtree = "";
         String preRightSubtree = "";
         int rootIndex = 0;
         int treeTransitionIndex = 0;

         // INORDER Gets left subtree for inorder
         for(int i = 0; i < in.length(); i++)
         {
            if(!(i >= in.length()-1))
            {
               if(!in.substring(i, i + 1).equals(root))
               {
                  inLeftSubtree += in.substring(i, i + 1);
               }
               else
               {
                  rootIndex = i;
                  i = in.length(); // stops loop
               }
            }
         }

         // INORDER Gets right subtree for inorder
         for(int i = rootIndex + 1; i < in.length(); i++)
         {
            if(!(i >= in.length() -1))
            {
               if(!in.substring(i, i + 1).equals(root))
               {
                  inRightSubtree += in.substring(i, i + 1);
               }
               else
               {
                  i = in.length(); // stops loop
               }
            }
            else
            {
               inRightSubtree += in.substring(i);
            }
         }

         // PREORDER Gets left subtree for preorder
         if(inLeftSubtree.length() <= 1)
         {
            preLeftSubtree += inLeftSubtree;
         }
         else
         {
            for(int i = 1; i < pre.length(); i++)
            {
               if(!(i >= pre.length() - 1))
               {
                     for(int k = 0; k < inLeftSubtree.length(); k++)
                     {
                        System.out.println(inLeftSubtree.substring(k, k + 1) + " : " + pre.substring(i, i + 1));

                        if(k >= inLeftSubtree.length() - 1)
                        {
                           if(inLeftSubtree.substring(k).equals(pre.substring(i, i + 1)))
                           {
                              preLeftSubtree += pre.substring(i, i + 1);
                           }
                           treeTransitionIndex = i;
                           i = pre.length();
                        }
                        else if (inLeftSubtree.substring(k, k + 1).equals(pre.substring(i, i + 1)))
                        {
                           k = inLeftSubtree.length();
                           preLeftSubtree += pre.substring(i, i + 1);
                        }
                     }
               }
               else
               {
                  for(int k = 0; k < inLeftSubtree.length(); k++)
                  {

                     if(k >= inLeftSubtree.length() - 1)
                     {
                        if(inLeftSubtree.substring(k).equals(pre.substring(i, i + 1)))
                        {
                           preLeftSubtree += pre.substring(i);
                        }
                        else
                        {
                           i = pre.length();
                        }
                     }
                     else if (inLeftSubtree.substring(k, k + 1).equals(pre.substring(i)))
                     {
                        k = inLeftSubtree.length();
                        preLeftSubtree += pre.substring(i);
                     }
                  }
                  treeTransitionIndex = i;
               }
            }
         }

         System.out.println(treeTransitionIndex);

         // PREORDER Gets right subtree for preorder
         if(inRightSubtree.length() <= 1)
         {
            preRightSubtree += inRightSubtree;
         }
         else
         {

            for(int i = treeTransitionIndex + 1; i < pre.length(); i++)
            {
               if(!(i >= pre.length() - 1))
               {
                  for(int k = 0; k < inRightSubtree.length(); k++)
                  {
                     if(k >= inRightSubtree.length() - 1)
                     {
                        if(inRightSubtree.substring(k).equals(pre.substring(i, i + 1)))
                        {
                           preRightSubtree += pre.substring(i, i + 1);
                        }
                     }
                     else if (inRightSubtree.substring(k, k + 1).equals(pre.substring(i, i + 1)))
                     {
                        System.out.println("2nd reached");
                        k = inRightSubtree.length();
                        preRightSubtree += pre.substring(i, i + 1);
                     }
                  }
               }
               else if (i == pre.length() - 1)
               {
                  for(int k = 0; k < inRightSubtree.length(); k++)
                  {

                     if(k >= inRightSubtree.length() - 1)
                     {
                        if(inRightSubtree.substring(k).equals(pre.substring(i)))
                        {
                           preRightSubtree += pre.substring(i);
                        }
                     }
                     else if (inRightSubtree.substring(k, k + 1).equals(pre.substring(i)))
                     {
                        k = inRightSubtree.length();
                        preRightSubtree += pre.substring(i);
                     }
                  }
               }
            }
         }

         System.out.println("Inorder: " + inLeftSubtree + " and " + inRightSubtree);

         System.out.println("Preorder: " + preLeftSubtree + " and " + preRightSubtree);

         // Initialization
         BinaryTree<T> leftChild = new BinaryTree<>(preLeftSubtree, inLeftSubtree);
         BinaryTree<T> rightChild = new BinaryTree<>(preRightSubtree, inRightSubtree);
         setTree((T)root, leftChild, rightChild);
      }
      else
      {
         // Leaf/Singular Node
         if(pre == null || pre.equals(""))
         {
            new BinaryTree<>();
         }
         else
         {
            new BinaryTree<>((T) pre, null, null);
         }
      }

   }

   public void setTree(T rootData, BinaryTreeInterface<T> leftTree,
                                   BinaryTreeInterface<T> rightTree)
   {
      initializeTree(rootData, (BinaryTree<T>)leftTree,
                               (BinaryTree<T>)rightTree);
   } // end setTree

   public void setRootData(T rootData)
   {
      root.setData(rootData);
   } // end setRootData
   
   public T getRootData()
   {
      if (isEmpty())
         throw new EmptyTreeException();
      else
         return root.getData();
   } // end getRootData
   
   public boolean isEmpty()
   {
      return root == null;
   } // end isEmpty
   
   public void clear()
   {
      root = null;
   } // end clear
   
   protected void setRootNode(BinaryNode<T> rootNode)
   {
      root = rootNode;
   } // end setRootNode
   
   protected BinaryNode<T> getRootNode()
   {
      return root;
   } // end getRootNode
   
   private void initializeTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree)
   {
      root = new BinaryNode<>(rootData);
      
      if ((leftTree != null) && !leftTree.isEmpty())
         root.setLeftChild(leftTree.root);
      
      if ((rightTree != null) && !rightTree.isEmpty())
      {
         if (rightTree != leftTree)
            root.setRightChild(rightTree.root);
         else
            root.setRightChild(rightTree.root.copy());
      } // end if
      
      if ((leftTree != null) && (leftTree != this))
         leftTree.clear();
      
      if ((rightTree != null) && (rightTree != this))
         rightTree.clear();
   } // end initializeTree
   
   /** -------------------------------------------------------------------- */
   /** Task 1: Implement the 4 methods
    *     . In trees.BinaryTree.java
    *          1. public void postorderTraverse();
    *          2. private void postorderTraverse(trees.BinaryNode<T> node)
    *          3. public void postorderTraverse_callBinaryNodeMethod()
    *     . In trees.BinaryNode.java
    *          4. public void postorderTraverse_binaryNodeMethod() */
   
   /** calls postorderTraverse(BinaryNode node)
    * prints (using post-order traversal) all nodes in the "whole" tree */
   public void postorderTraverse()
   {
      postorderTraverse(root);
   }
   
   /** A Recursive Method in the BinaryTree Class
    * prints (using post-order traversal) all nodes in the subtree rooted at this node.*/
   private void postorderTraverse(BinaryNode<T> node)
   {
      if(node != null)
      {
         postorderTraverse(node.getLeftChild());
         postorderTraverse(node.getRightChild());
         System.out.print(node.getData());
      }
   }
 
   /** The following calls postorderTraverse_binaryNodeMethod(), which is a recursive binaryNode class method   
    * prints (using post-order traversal) all nodes in the "whole" tree */
   public void postorderTraverse_callBinaryNodeMethod()
   {
      if(root != null)
      {
         root.postorderTraverse_binaryNodeMethod();
      }
   }
   
   /** -------------------------------------------------------------------- */
   /** Task 2: Implement the 2 methods
    *     . In trees.BinaryTree.java
    *          1. public int getHeight_callBinaryNodeMethod()
    *     . In trees.BinaryNode.java
    *          2. public int getHeight_binaryNodeMethod()*/
   
   /** calls getHeight(BinaryNode node)
   @return  The height of the "whole" tree */
   public int getHeight()
   {
	   return getHeight(root);
   } // end getHeight
   
   /** A Recursive Method in the trees.BinaryTree Class
    * Computes the height of the subtree rooted at this node.
   @return  The height of the subtree rooted at this node. */
   private int getHeight(BinaryNode<T> node)
   {
      int height = 0;
      if (node != null)
         height = 1 + Math.max(getHeight(node.getLeftChild()),
                               getHeight(node.getRightChild()));
      return height;
   } // end getHeight
   
   /** The following calls getHeight_binaryNodeMethod() which is a recursive binaryNode class method
    * Computes the height of the "whole" tree.
   @return  The height of the "whole" tree. */
   public int getHeight_callBinaryNodeMethod()
   {
      if(root != null)
      {
	     return root.getHeight_binaryNodeMethod();
      }

      return 0;
   } // end getHeight_callBinaryNodeMethod

   /** -------------------------------------------------------------------- */
   /** Task 3: Implement the 2 methods
    *     . In trees.BinaryTree.java
    *          1. public int getNumberOfNodes()
    *          2. private int getNumberOfNodes(trees.BinaryNode<T> node)*/
   
   /** calls getNumberOfNodes(BinaryNode node)
   @return  The number of nodes in the "whole" tree */
   public int getNumberOfNodes()
   {
       return getNumberOfNodes(root);
   } // end getNumberOfNodes
   
   /** A Recursive Method in the BinaryTree Class
    * Counts the nodes in the subtree rooted at this node.
   @return  The number of nodes in the subtree rooted at this node. */
   private int getNumberOfNodes(BinaryNode<T> node)
   {
      int num = 0;
      if(node != null)
      {
         num = 1 + getNumberOfNodes(node.getLeftChild()) + getNumberOfNodes(node.getRightChild());
         
      }

      return num;
   } // end getNumberOfNodes
   
   /** The following calls getNumberOfNodes_binaryNodeMethod() which is a recursive binaryNode class method
    * Counts the nodes in the "whole" tree
   @return  The number of nodes in the "whole" tree. */
   public int getNumberOfNodes_callBinaryNodeMethod()
   {
	   int numberOfNodes = 0;
	   if (root != null)
		   numberOfNodes = root.getNumberOfNodes_binaryNodeMethod();
	   return numberOfNodes;
   } // end getNumberOfNodes_callBinaryNodeMethod
   
} // end trees.BinaryTree
