# Post
Csc668/868

Example Usage:
" Create some nodes "
node1 := BinTree new: 'A'.
node2 := BinTree new: 'B'.
node3 := BinTree new: 'C'.
node4 := BinTree new: 'D'.
node5 := BinTree new: 'E'.
node6 := BinTree new: 'F'.
node7 := BinTree new: 'G'.
node8 := BinTree new: 'H'.

" Form the tree "
node5 addLeftKid: node2.
node5 addRightKid: node7.
node2 addLeftKid: node1.
node2 addRightKid: node3.
node7 addLeftKid: node6.
node7 addRightKid: node8.
node3 addRightKid: node4.

" Print a parent then its left and right child "
Transcript show: node5 getLabel;cr.
Transcript show: node5 getLeftKid getLabel;cr.
Transcript show: node5 getRightKid getLabel;cr;cr.

" Start traversal from tree root - E/node5 "
traversal _ InorderTraversal new: node5.
" Navigate to first node in tree for inordertransversal "
nextLabel _ traversal first.
Transcript show: 'The inorder transversal of the tree starts at: '.
nextLabel print. "Should be A"
