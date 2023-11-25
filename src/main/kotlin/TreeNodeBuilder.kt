data class TreeNode(val value: String, val parent: TreeNode? = null) {
    val children = mutableListOf<TreeNode>()

    fun addChild(child: TreeNode) {
        children.add(child)
    }

    fun parent(): TreeNode {
        return parent ?: throw IllegalStateException("Cannot go back to parent node. Already at the root.")
    }
}

class TreeNodeBuilder(root: TreeNode? = null) {
    private var currentNode: TreeNode = root ?: TreeNode("")

    fun value(value: String): TreeNodeBuilder {
        val node = TreeNode(value, currentNode)
        currentNode.addChild(node)
        return this
    }

    fun child(): TreeNodeBuilder {
        return TreeNodeBuilder(currentNode.children.last())
    }

    fun parent(): TreeNodeBuilder {
        return TreeNodeBuilder(currentNode.parent())
    }

    fun build(): TreeNode {
        return currentNode
    }
}

fun buildTree(): TreeNodeBuilder {
    return TreeNodeBuilder()
}

fun main() {
    val tree = buildTree()
        .value("Root")
        .child()
        .value("Child 1")
        .child()
        .value("Grandchild 1.1")
        .child()
        .value("Grandchild 1.2")
        .parent()
        .parent()
        .value("Child 2")
        .child()
        .value("Grandchild 2.1")
        .parent()
        .build()
    println(tree.children)
    printTree(tree)
}

fun printTree(node: TreeNode, level: Int = 0) {
    val indentation = "  ".repeat(level)
    println("$indentation${node.value}")
    for (child in node.children) {
        printTree(child, level + 1)
    }
}