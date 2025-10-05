import csv
import time
def csvToList(file_path):
    product_list = [] #Product List
    try:
        with open(file_path, 'r') as csv_file: #Open file in read mode
            csv_reader = csv.DictReader(csv_file)
            
            for row in csv_reader: #Add each row to the product list
                product_list.append(row)

        return product_list

    except FileNotFoundError:
        print(f"Error: File '{file_path}' not found.")
        return []
    except Exception as e:
        print(f"An error occurred: {e}")
        return []

def linearSearch(product_list, product_id):
    # Start timing the search
    start_time = time.time()
    
    for product in product_list:
        if product['product_id'] == product_id:
            # Calculate search time
            search_time = time.time() - start_time
            print(f"Linear search found product in {search_time:.6f} seconds")
            return product
    
    # If product not found, calculate total search time
    search_time = time.time() - start_time
    print(f"Linear search completed in {search_time:.6f} seconds - Product not found")
    return None
    
def productHashTable(product_list):
    product_dict = {} #Product Dictionary
    
    for product in product_list:
        product_dict[product['product_id']] = product
    return product_dict

def hashLookup(product_dict, product_id):
    start_time = time.time()
    
    product = product_dict.get(product_id)
    search_time = time.time() - start_time
    
    if product:
        print(f"Hash lookup found product in {search_time:.6f} seconds")
    else:
        print(f"Hash lookup completed in {search_time:.6f} seconds - Product not found")
        
    return product
    
def displayProduct(product):

    if product:
        print("\nProduct Found:")
        print(f"ID: {product['product_id']}")
        print(f"Name: {product['name']}")
        print(f"Category: {product['category']}")
        print(f"Price: ${float(product['price']):.2f}")
    else:
        print("\nProduct not found.")

def main():
    file_path = "products.csv"

    product_list = csvToList(file_path)
        
    if not product_list:
        print("No products loaded. Exiting program.")
        return
        
    print(f"Loaded {len(product_list)} products.")

    product_dict = productHashTable(product_list)
        
    while True:
        print("\n" + "="*50)
        print("Product Search System")
        print("="*50)
            
        product_id = input("\nEnter a product ID to search (e.g., P0036) or 'q' to quit: ")
            
        if product_id.lower() == 'q':
            print("Exiting program")
            break
            
        print("\nPerforming linear search...")
        product_linear = linearSearch(product_list, product_id)
        displayProduct(product_linear)
            
        print("\nPerforming hash-based search...")
        product_hash = hashLookup(product_dict, product_id)
        displayProduct(product_hash)

if __name__ == "__main__":
    main()