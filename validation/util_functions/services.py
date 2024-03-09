def convert_data(data):
    x = len(data[0])
    y = len(data)

    total_data = []

    for i in range(0,x):
        temp=[]
        for j in range(0,y):
            temp.append(float(data[j][i]))
        total_data.append(temp)

    return total_data


def map_data_of_ca_cluster(data, total_data_points):

    labels = [0] * total_data_points

    x = len(data)

    for i in range(x):
        for j in data[i]:
            labels[j]=i

    return labels


