// HW 3
// By Ethan Ruoff, Jason Jin, and Andrea Lin

#include <string.h>
#include <stdlib.h>
#include <stdio.h>

#define ALPHABET_LENGTH 26
#define OPERATION_BUF_SIZE 7 /* Large enough to cover the word 'search' and '\0' */
#define NAME_BUF_SIZE 22
#define ASCII_A 97

/* Basic trie node -- also, keep track of number of nodes below this one. */
typedef struct node
{
    int prefix_count;
    /* Allocate +1 for the the pointer to the end-of-string marker. Needed
       for the search feature. */
    struct node *children[ALPHABET_LENGTH + 1];
} trie_node;

// Easy way to construct a struct
trie_node *create_node()
{
    return (trie_node *)calloc(1, sizeof(trie_node));
}

/* Recursive Function that adds a word to the Trie */
void insert(trie_node *root, char *in, int index)
{
    // Check if we reached the end of input
    if (*(in+index) == '\0')
    {
        root->children[26] = create_node();
        root->prefix_count++;
        //puts("Reached end of string");
        return;
    }

    int new_char = *(in+index) - ASCII_A;
    // If the next node doesn't exist make it
    if (root->children[new_char] == NULL)
    {
        //printf("%c\n", *(in+index));
        root->children[new_char] = create_node();
    }
    root->prefix_count++;
    insert(root->children[new_char], in, index + 1);
}

/* Recursive Function that traverses the Trie to the last letter of input then outputs the prefix count */
void find(trie_node *root, char *in, int index)
{
    // Check if we reached the end of input
    if (*(in+index) == '\0')
    {
        //puts("Find Reached end of string");
        printf("%d\n", root->prefix_count);
        return;
    }

    int next_char = *(in+index) - ASCII_A;
    // If the next node for the input doesn't exist then output 0
    if (root->children[next_char] == NULL)
    {
        puts("0");
        return;
    }
    find(root->children[next_char], in, index+1);
}

/* Recursive Function that traverses the Trie to the last letter of input then prints if there's a word there */
void search(trie_node *root, char *in, int index)
{
    // Check if in[index] is null (when we're at the last letter)
    if (*(in+index) == '\0')
    {
        //puts("Find Reached end of string");
        // Check if there's a word there
        if (root->children[26] == NULL) {
            puts("no");
        }
        else {
            puts("yes");
        }
        return;
    }

    int next_char = *(in+index) - ASCII_A;
    if (root->children[next_char] == NULL)
    {
        puts("no");
        return;
    }
    search(root->children[next_char], in, index+1);
}

/* Frees all of the memory at the end of the program */
void free_trie(trie_node *root)
{
    if (root != NULL) {
        for (int i=0; i < ALPHABET_LENGTH+1; i++) 
        {
            free_trie(root->children[i]);
        }
        free(root);
    }
}

int main(int argc, char const *argv[])
{
    int num_vals;
    // Reads input as int
    scanf("%d", &num_vals);

    // Create root node for contacts
    trie_node *root = create_node();

    // For each query
    for (int i = 0; i < num_vals; i++)
    {
        char in[OPERATION_BUF_SIZE + NAME_BUF_SIZE+2], *inptr;
        inptr = in;
        scanf(" %[^\n]s", in);
        if (in[0] == 'a')
        {
            insert(root, inptr, 4);
            //find(root, inptr, 4);
        }
        else if (in[0] == 'f')
        {
            find(root, inptr, 5);
        }
        else if (in[0] == 's')
        {
            search(root, inptr, 7);
        }
    }
    free_trie(root);
    return 0;
}
