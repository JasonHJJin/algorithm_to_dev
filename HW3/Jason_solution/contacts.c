#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

#define ALPHABET_LENGTH 26
#define OPERATION_BUF_SIZE 7 /* Large enough to cover the word 'search' and '\0' */
#define NAME_BUF_SIZE 22

/* Basic trie node -- also, keep track of number of nodes below this one. */
typedef struct node
{
    int prefix_count;
    struct node *children[ALPHABET_LENGTH];
    bool length; // avoid duplicates
} trie_node;

static void die(const char *message)
{
    perror(message);
    exit(1);
}

static void free_trie(trie_node *copy)
{
    struct node **p = copy->children;
    // free all the strings
    while (*p)
        free(*p++);
    // free the overall array
    free(copy);
}

static trie_node *create_node()
{
    trie_node *temp = (trie_node *)calloc(1, sizeof(trie_node));
    if (temp == NULL)
    {
        die("calloc failed");
    }
    temp->prefix_count = 0;
    temp->length = false;
    return temp;
}

static void addNode(trie_node *node, char *str)
{
    if (node == NULL)
    {
        die("addNode is null");
    }
    int len = strlen(str);
    for (int i = 0; i < len; i++)
    {
        int idx = str[i] - 'a';
        if (node->children[idx] == NULL)
        {
            node->children[idx] = create_node();
        }
        node = node->children[idx];
        node->prefix_count++;
    }
    node->length = true;
}

static int findNode(trie_node *node, char *str)
{
    if (node == NULL)
    {
        die("findNode is null");
    }
    trie_node *temp = node;
    for (int i = 0; i < strlen(str); i++)
    {
        int idx = str[i] - 'a';
        temp = temp->children[idx];
        if (!temp)
        {
            return 0;
        }
    }
    return temp->prefix_count;
}

static bool searchNode(trie_node *node, char *str)
{
    if (node == NULL)
    {
        die("searchNode is null");
    }
    trie_node *temp = node;
    for (int i = 0; i < strlen(str); i++)
    {
        int idx = str[i] - 'a';
        temp = temp->children[idx];
        if (!temp)
        {
            return false;
        }
    }
    return temp->length;
}

int main()
{
    // Scan the integer input
    int input;
    if (scanf("%d", &input))
    {
        trie_node *head = create_node();
        if (head == NULL)
        {
            die("head is null");
        }
        char oper[OPERATION_BUF_SIZE];
        char str[NAME_BUF_SIZE];
        for (int i = 0; i < input; i++)
        {
            scanf("%s %s", oper, str);
            if (strcmp(oper, "add") == 0)
            {
                addNode(head, str);
            }
            else if (strcmp(oper, "find") == 0)
            {
                printf("%d\n", findNode(head, str));
            }
            else if (strcmp(oper, "search") == 0)
            {
                if (searchNode(head, str) == false)
                {
                    puts("no");
                }
                else
                {
                    puts("yes");
                }
            }
            else
            {
                die("operation not possible");
            }
        }
        free_trie(head);
    }
    else
    {
        die("invalid input");
    }
    return 0;
}