@DslMarker
annotation class TreeNodeDslMarker

@TreeNodeDslMarker
data class TreeNode2(val value: String) {
    val children = mutableListOf<TreeNode2>()

    fun addChild(child: TreeNode2) {
        children.add(child)
    }
}

class TreeNodeBuilder2 {
    private val root = TreeNode2("")
    private var currentNode = root

    fun value(value: String) {
        currentNode = TreeNode2(value)
        root.addChild(currentNode)
    }

    fun child(block: TreeNodeBuilder2.() -> Unit) {
        val childBuilder = TreeNodeBuilder2()
        childBuilder.block()
        currentNode.addChild(childBuilder.build())
    }

    fun build(): TreeNode2 {
        return root
    }
}

fun buildTree2(block: TreeNodeBuilder2.() -> Unit): TreeNode2 {
    val builder = TreeNodeBuilder2()
    builder.block()
    return builder.build()
}

fun main() {
    val tree = buildTree2 {
        value("Root")
        child {
            value("Child 1")
            child {
                value("Grandchild 1.1")
            }
            child {
                value("Grandchild 1.2")
            }
        }
        child {
            value("Child 2")
            child {
                value("Grandchild 2.1")
            }
        }
    }

    printTree2(tree)
}

fun printTree2(node: TreeNode2, level: Int = 0) {
    val indentation = "  ".repeat(level)
    println("$indentation${node.value}")
    for (child in node.children) {
        printTree2(child, level + 1)
    }
}