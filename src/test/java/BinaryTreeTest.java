import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import trees.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class BinaryTreeTest
{
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setStreams() {
        System.setOut(new PrintStream(out));
    }

    @AfterEach
    public void restoreInitialStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void emptyTree()
    {
        // empty tree
        BinaryTree<String> tree = new BinaryTree<>();

        // Tests both public and private postorderTraverse methods in BinaryTree
        tree.postorderTraverse();

        // Tests postorderTraverse_BinaryNodeMethod in BinaryNode and postorderTraverse_
        tree.postorderTraverse_callBinaryNodeMethod();

        assertEquals("", out.toString());

        // Tests height and nodes
        assertEquals(0, tree.getHeight());
        assertEquals(0, tree.getHeight_callBinaryNodeMethod());

        assertEquals(0, tree.getNumberOfNodes());
        assertEquals(0, tree.getNumberOfNodes_callBinaryNodeMethod());
    }

    @Test
    public void oneNodeTree()
    {
        // root
        BinaryTree<String> tree = new BinaryTree<>("A");

        // Tests both public and private postorderTraverse methods in BinaryTree
        tree.postorderTraverse();
        System.out.print("\n");

        // Tests postorderTraverse_BinaryNodeMethod in BinaryNode and postorderTraverse_
        tree.postorderTraverse_callBinaryNodeMethod();
        System.out.print("\n");

        assertEquals("A\nA\n", out.toString());

        // Tests height and nodes
        assertEquals(1, tree.getHeight());
        assertEquals(1, tree.getHeight_callBinaryNodeMethod());

        assertEquals(1, tree.getNumberOfNodes());
        assertEquals(1, tree.getNumberOfNodes_callBinaryNodeMethod());
    }

    @Test
    public void linearTree()
    {

        // nodes
        BinaryTree<String> eTree = new BinaryTree<>("E");
        BinaryTree<String> dTree = new BinaryTree<>("D", eTree, null);
        BinaryTree<String> cTree = new BinaryTree<>("C", dTree, null);
        BinaryTree<String> bTree = new BinaryTree<>("B", cTree, null);

        // root
        BinaryTree<String> tree = new BinaryTree<>("A", bTree, null);

        /*
        Given Tree:
             A
           /
          B
         /
        C
         \
          D
           \
            E
        */


        // Tests both public and private postorderTraverse methods in BinaryTree
        tree.postorderTraverse();
        System.out.print("\n");

        // Tests postorderTraverse_BinaryNodeMethod in BinaryNode and postorderTraverse_
        tree.postorderTraverse_callBinaryNodeMethod();
        System.out.print("\n");

        assertEquals("EDCBA\nEDCBA\n", out.toString());

        // Tests height and nodes
        assertEquals(5, tree.getHeight());
        assertEquals(5, tree.getHeight_callBinaryNodeMethod());

        assertEquals(5, tree.getNumberOfNodes());
        assertEquals(5, tree.getNumberOfNodes_callBinaryNodeMethod());
    }

    @Test
    public void completeBinaryTree()
    {
        // nodes
        BinaryTree<String> eTree = new BinaryTree<>("E");
        BinaryTree<String> dTree = new BinaryTree<>("D");
        BinaryTree<String> cTree = new BinaryTree<>("C");
        BinaryTree<String> bTree = new BinaryTree<>("B", dTree, eTree);

        // root
        BinaryTree<String> tree = new BinaryTree<>("A", bTree, cTree);

        /*
        Given Tree:
            A
           / \
          B   C
         / \
        D   E
        */


        // Tests both public and private postorderTraverse methods in BinaryTree
        tree.postorderTraverse();
        System.out.print("\n");

        // Tests postorderTraverse_BinaryNodeMethod in BinaryNode and postorderTraverse_
        tree.postorderTraverse_callBinaryNodeMethod();
        System.out.print("\n");

        assertEquals("DEBCA\nDEBCA\n", out.toString());

        // Tests height and nodes
        assertEquals(3, tree.getHeight());
        assertEquals(3, tree.getHeight_callBinaryNodeMethod());

        assertEquals(5, tree.getNumberOfNodes());
        assertEquals(5, tree.getNumberOfNodes_callBinaryNodeMethod());
    }

    @Test
    public void fullBinaryTree()
    {
        // nodes
        BinaryTree<String> gTree = new BinaryTree<>("G");
        BinaryTree<String> fTree = new BinaryTree<>("F");
        BinaryTree<String> eTree = new BinaryTree<>("E");
        BinaryTree<String> dTree = new BinaryTree<>("D");
        BinaryTree<String> cTree = new BinaryTree<>("C", fTree, gTree);
        BinaryTree<String> bTree = new BinaryTree<>("B", dTree, eTree);

        // root
        BinaryTree<String> tree = new BinaryTree<>("A", bTree, cTree);

        /*
        Given Tree:
             A
           /   \
          B     C
         / \   / \
        D   E F   G
        */


        // Tests both public and private postorderTraverse methods in BinaryTree
        tree.postorderTraverse();
        System.out.print("\n");

        // Tests postorderTraverse_BinaryNodeMethod in BinaryNode and postorderTraverse_
        tree.postorderTraverse_callBinaryNodeMethod();
        System.out.print("\n");

        assertEquals("DEBFGCA\nDEBFGCA\n", out.toString());

        // Tests height and nodes
        assertEquals(3, tree.getHeight());
        assertEquals(3, tree.getHeight_callBinaryNodeMethod());

        assertEquals(7, tree.getNumberOfNodes());
        assertEquals(7, tree.getNumberOfNodes_callBinaryNodeMethod());
    }

    @Test
    public void uniqueTree()
    {
        // Leaves
        BinaryTree<String> dTree = new BinaryTree<>("D");
        BinaryTree<String> eTree = new BinaryTree<>("E");
        BinaryTree<String> gTree = new BinaryTree<>("G");

        // Subtrees:
        BinaryTree<String> fTree = new BinaryTree<>("F", null, gTree);
        BinaryTree<String> bTree = new BinaryTree<>("B", dTree, eTree);
        BinaryTree<String> cTree = new BinaryTree<>("C", fTree, null);

        BinaryTree<String> tree = new BinaryTree<>();
        tree.setTree("A", bTree, cTree);

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

        // Tests both public and private postorderTraverse methods in BinaryTree
        tree.postorderTraverse();
        System.out.print("\n");

        // Tests postorderTraverse_BinaryNodeMethod in BinaryNode and postorderTraverse_
        tree.postorderTraverse_callBinaryNodeMethod();
        System.out.print("\n");

        assertEquals("DEBGFCA\nDEBGFCA\n", out.toString());

        // Tests height and nodes
        assertEquals(4, tree.getHeight());
        assertEquals(4, tree.getHeight_callBinaryNodeMethod());

        assertEquals(7, tree.getNumberOfNodes());
        assertEquals(7, tree.getNumberOfNodes_callBinaryNodeMethod());
    }
}
