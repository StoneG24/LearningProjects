# Based on the algorithms presented in A tree based approach to improve traditional collision avoidance mechanisms of hashing by 
# S. Dhar, K. Pandey, M. Premalatha and G. Suganya 

class hashTable:
    def __init__(self, size=11): #base size of 11 for indexes 0-10
        self.size = size
        self.table = []
        i = 0
        while i < self.size:        # Creates an array at each index to hold BST
            self.table.append([])   # Using array representation of trees made insertion and searching simple using array indexes
            i += 1

    def _hash(self, key):
        return key % self.size             # Can be altered using any hash funciton
    
    def insert(self, key):
        index = self._hash(key)
        if self.table[index] == []:         
            self.table[index].append(key)   #If the BST is empty place the key as the root node
        else:
            element = 0
            while(len(self.table[index]) > element):   #Makes sure the key is placed in the lowest level of the array
                if self.table[index][element] == None: #if a node is to be created where the array has been populated with None
                    break
                elif key >= self.table[index][element]: #Moves to the nodes right child (larger value)
                    element = 2*element+2
                else:                                   #Moves to the nodes left child (smaller value)
                    element = 2*element+1
            while(len(self.table[index]) <= element):    
                self.table[index].append(None)          #Populates null elements in between nodes as None 
            self.table[index][element] = (key)
    
    def search(self, key):
        index = self._hash(key)
        element = 0
        while(len(self.table[index]) > element):
            if key == self.table[index][element]:       
                return print("Value {0} found at index: {1}, Node: {2}".format(key, index, element))
            if key > self.table[index][element]:
                element = 2*element+2
            else:
                element = 2*element+1
        print("Value {0} not found".format(key))        

hash_table = hashTable(11)
hash_table.insert(25)
hash_table.insert(36)
hash_table.insert(20)
hash_table.insert(50)
hash_table.insert(15)
hash_table.insert(22)
hash_table.insert(10)
hash_table.insert(35)
hash_table.insert(27)
hash_table.insert(30)
hash_table.insert(40)
hash_table.insert(3)
hash_table.search(3)
hash_table.search(36)
hash_table.search(16)
print(vars(hash_table))