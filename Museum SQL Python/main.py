import mysql.connector
from tabulate import tabulate

#Function for guest features
def guest_view(cur):
    print('\nWelcome to guest view')
    while(True):
        print('What would you like to see?')
        print('1-Art Objects')
        print('2-Artists')
        print('3-Exhibitions')
        print('4-Quit')
        selection = input('Please enter your choice: ')
        print('')
        while selection not in ['1', '2', '3', '4']:
            selection = input('\nInvalid input.\nPlease enter a number from 1-4: ')
        if selection == '4':
            print('Goodbye!')
            break
        elif selection == '1':
            choice = input('Would you like to see:\n1-Paintings\n2-Sculptures\n3-Statues\n4-Other\nPlease enter your choice here: ')
            if choice == '1':
                cur.execute('''SELECT A.Title, A._Description, A.Year_created, A.Epoch, A.Origin, P.paint_type, P.drawn_on,
                             P.Style FROM ART_OBJECT AS A JOIN PAINTING AS P ON A.id_no = P.id_no''')
                display_data(cur)
            elif choice == '2':
                cur.execute('''SELECT A.Title, A._Description, A.Year_created, A.Epoch,
                        P.Material, P.Height, P._Weight, P.Style FROM ART_OBJECT AS A JOIN SCULPTURE AS P ON A.id_no = P.id_no''')
                display_data(cur)
            elif choice == '3':
                cur.execute('''SELECT A.Title, A._Description, A.Year_created, A.Epoch,
                        P.Material, P.Height, P._Weight, P.Style FROM ART_OBJECT AS A JOIN STATUE AS P ON A.id_no = P.id_no''')
                display_data(cur)
            elif choice == '4':
                cur.execute('''SELECT A.Title, A._Description, A.Year_created, A.Epoch,
                        P.art_type, P.Style FROM ART_OBJECT AS A JOIN OTHER AS P ON A.id_no = P.id_no''')
                display_data(cur)
            else:
                print('invalid input')
                continue

        elif selection == '2':
            cur.execute('''SELECT Artist_Name , date_born, main_style, date_died, Epoch, origin_country, _description
                         FROM ARTIST''')
            display_data(cur)

        elif selection == '3':
            cur.execute('''SELECT Exhibition_Name, startdate, enddate FROM EXHIBITION''')
            display_data(cur)

#Function to display data            
def display_data(cur):
    result = cur.fetchall()
    print(tabulate(result, headers=cur.column_names, tablefmt='psql'))

#Function for data_entry users aka employees
def data_entry(cur):
    while(1):
        print('\nWhat would you like to do?')
        print('1 - Add New Data')
        print('2 - Modify exisiting data')
        print('3 - Delete exsisting data')
        print('4 - Display Data')
        print('5 - Quit')
        choice = input('Please enter your choice from 1-4: ')
        while choice not in ['1', '2', '3', '4', '5']:
            choice = input('Invalid input! Please select a number from 1-4: ')
        if choice == '1':
            print('\nAvailable tables to add data are:\n')
            cur.execute("SELECT table_name FROM information_schema.tables WHERE table_schema = 'museum'")
            options = []
            for table in [tables[0] for tables in cur.fetchall()]:
                options.append(table)
            print(*options, sep=', ')
            tbl = input('Which table would you like to add data to: ')
            while(tbl not in options):
                tbl = input('\nInvalid entry. Table does not exist.\nWhich table would you like to add data to: ')
            try: 
                cur.execute(f'SELECT * FROM {tbl}')
            except mysql.connector.Error:
                print('Error, invalid name')
                return
            values = []
            for i in range(len(cur.description)):
                descript = cur.description[i]
                print(f'Please enter the data you would like to add to column {descript[0]}: ')
                if descript[6] == 0:
                    print('Note: Attribute may not be NULL')
                    
                else:
                    print('Note: If left empty, attribute will be set as NULL')
                
                values.append(input())
            unpack = ", ".join(["'"+e+"'" for e in values])
            try:
                cur.execute(f'INSERT INTO {tbl} VALUES ({unpack})')
                cur.execute(f'SELECT * FROM {tbl}')
                print('New Table:\n')
                display_data(cur)
            except mysql.connector.Error as err:
                print(err)

        elif choice == '2':
            print('\nAvailable tables to modify: ')
            cur.execute("SELECT table_name FROM information_schema.tables WHERE table_schema = 'museum'")
            options = []
            for table in [tables[0] for tables in cur.fetchall()]:
                options.append(table)
            print(*options, sep=', ')
            tbl = input('Select table to modify: ')
            while(tbl not in options):
                tbl = input('\nInvalid input. Table does not exist.\nSelect table to modify: ')
            try:
                cur.execute(f'SELECT * FROM {tbl}')
                print('Current Table:')
                display_data(cur)
            except mysql.connector.Error:
                print('Error, name invalid')
                return
            print(f'The attributes of {tbl} are :')
            options = []
            for i in range(len(cur.description)):
                desc = cur.description[i]
                options.append(desc[0])
            print(*options, sep=', ')
            print('\nWhich attribute would you like to change:\nNOTE: can only change one at a time.')
            attrib = input()
            while(attrib not in options):
                attrib = input('\nInvalid entry. Attribute does not exist.\nWhich attribute would you like to change: ')
            try:
                cur.execute(f'SELECT {attrib} FROM {tbl}')
            except mysql.connector.Error as e:
                print(e)
                return
            condition_attrib = input(f'Please select a attribute from the {tbl} table: ')
            while(condition_attrib not in options):
                condition_attrib = input(f'\nInvalid entry. Conditional attribute does not exist.\nPlease select a conditional attribute from the {tbl} table: ')
            condition = input(f'Please select a value from the {condition_attrib} column associated with the modification of {attrib}\n(Note: If the value chosen is not from the column, nothing will be modified):\n')
            new_values = input(f'Please enter the new value for {attrib}: ')
            try:
                cur.execute(f"UPDATE {tbl} SET {attrib} = '{new_values}' WHERE {condition_attrib} = '{condition}'")
                cur.execute(f'SELECT * FROM {tbl}')
                print('Modified table\n')
                display_data(cur)
                print('')
            except mysql.connector.Error as err:
                print(err)
        elif choice == '3':
            print('\nAvailable tables are:')
            cur.execute("SELECT table_name FROM information_schema.tables WHERE table_schema = 'museum'")
            options = []
            for table in [tables[0] for tables in cur.fetchall()]:
                options.append(table)
            print(*options, sep=', ')
            tbl = input('\nPlease enter the name of the table you want to delete from:')
            while(tbl not in options):
                tbl = input('\nInvalid entry. Table does not exist.\nWhich table would you like to delete from: ')
            try:
                cur.execute(f'SELECT * FROM {tbl}')
                print('Table in current state:\n')
                display_data(cur)
                print('')
            except mysql.connector.Error as err:
                print("Something went wrong: {}".format(err))
                return
            print(f'The attributes in {tbl} are :')
            options = []
            for i in range(len(cur.description)):
                desc = cur.description[i]
                options.append(desc[0])
            print(*options, sep=', ')
            attrib = input('\nWhich attribute would you like to use as a condition to delete: ')
            while(attrib not in options):
                attrib = input('\nInvalid entry. Attribute does not exist.\nWhich attribute would you like to use as a condition to delete: ')
            print(f'Which value from {attrib} would you like to use as your condition for deletion?')
            condition = input(f'Deleting rows when {attrib} = ')
            try:
                cur.execute(f"DELETE FROM {tbl} WHERE {attrib}= '{condition}'")
                cur.execute(f'SELECT * FROM {tbl}')
                print(f'\n\nTable {tbl} after your deletion: \n')
                display_data(cur)
            except mysql.connector.Error as err:
                print("Something went wrong: {}".format(err))
                return

        elif choice == '4':
            print('Available tables are:\n')
            cur.execute("SELECT table_name FROM information_schema.tables WHERE table_schema = 'museum'")
            options = []
            for table in [tables[0] for tables in cur.fetchall()]:
                options.append(table)
            print(*options, sep=', ')
            tbl = input('\nPlease enter the name of the table you want to view: ')
            cur.execute(f'SELECT * FROM {tbl}')
            display_data(cur)
        elif choice == '5':
            print('Goodbye!')
            exit()       
    return

#Function for admin features
def admin_view(cur):
    while True:
        print('\nWould you like to:\n1-Execute an SQL command\n2-Run an SQL script\n3-Quit')
        choice = input('Please enter your selection: ')
        while choice not in ['1','2','3']:
            choice = input('Invalid input. Please enter a valid choice: ')
        if choice == '1':
            while True:
                query = input('\nPlease enter the SQL command that you want to execute: ')
                try:
                    cur.execute(f'{query}')
                    querysplit = query.split()
                    if querysplit[0] == 'SELECT':
                        display_data(cur)
                except mysql.connector.Error as e:
                    print(e)
                cont = input('Would you like to execute another command?\nY for yes, anything else for no: ')
                if cont not in ['Y', 'y']:
                    break
        elif choice == '2':
            while True:
                print('\nPlease enter the directory and file name of the script you want to run without any quotations: ')
                filepath = input()    
                fd = open(f'{filepath}', 'r')
                sqlFile = fd.read()
                fd.close()
                sqlCommands = sqlFile.split(';')

                for command in sqlCommands:
                    try:
                        if command.strip() != '':
                            cur.execute(command)
                    except mysql.connector.Error as msg:
                        print("Command skipped: ", msg)
                
                cont = input('Would you like to execute another file? Y for yes, anything else for no: ')
                if cont not in ['Y', 'y']:
                    break
        elif choice == '3':
            print('\nGoodbye!')
            exit()
    
if __name__ == "__main__":
    
    print("\nWelcome to the Art Museum Database!")
    print("In order to proceed please select your role from the list below:")
    print("1-Administrator")
    print("2-Employee")
    print("3-Guest")
    print("0-Quit")

    selection = input("please type 1, 2, or 3 to select your role: ")
    while selection not in ['1', '2', '3', '0']:
        selection = input("Invalid input, please enter either 1, 2, 3, or 0: ")
    
    if selection == '0':
        print('Goodbye!')
        exit()

    if selection in ['1','2']:
        username= input("user name: ")
        passcode= input("password: ")

    else:
        username="guest"
        passcode=None
    
    try :
        cnx = mysql.connector.connect(
        user = username,
        password = passcode,
        autocommit = True
        )
        if (cnx.is_connected()):
            print("Connection Successful")
    
    except mysql.connector.Error as e:
        print(e)
        print('Exiting...')
        exit()   

    cur = cnx.cursor(buffered=True)
    cur.execute("use museum")


    if selection == '3':
        guest_view(cur)
    elif selection == '2':
        data_entry(cur)
    else:
        admin_view(cur)
