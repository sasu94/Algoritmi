#include <iostream>
#include <cstring>
#include <cstdlib>
#include <climits>

using namespace std;


void print(int s[], int start, int end){
  for (int i=start; i<=end; i++)
    (i==end)?cout<<s[i]:cout<<s[i]<<" ";
}

void reconstruct_partition(int s[],int d[][100], int n, int k){
  if (k==1)
    print(s,1,n);
  else {
      reconstruct_partition(s,d,d[n][k],k-1);
      cout<<(" | ");
      print(s,d[n][k]+1,n);
    }
}

void partition(int s[], int n, int k){
  int m[10000][100];
  int d[10000][100];
  int p[10000];
  int cost;
  int i,j,x;
  p[0] = 0;
  for (i=1; i<=n; i++) {
    p[i]=p[i-1]+s[i];
    m[i][1] = p[i];
  }
  for (j=1; j<=k; j++) m[1][j] = s[1];
  for (i=2; i<=n; i++)
  for (j=2; j<=k; j++) {
    m[i][j] =INT_MAX;
    for (x=1; x<=(i-1); x++) {
      cost = max(m[x][j-1], p[i]-p[x]);
      if (m[i][j] > cost) {
        m[i][j] = cost;
        d[i][j] = x;
      }
    }
  }
  reconstruct_partition(s,d,n,k);
}


int main(int argc, char const *argv[]) {
  string s;
  getline(cin,s);

  do {
    int n=0;
    char* t=strtok(&s[0]," ");

    while(t!=NULL){
      if(isdigit(t[0]))
        n=atoi(t);
      t=strtok(NULL," ");
    }

    getline(cin,s);
    t=strtok(&s[0]," ");
    int i=0;
    int nM=0,nP=0;
    while(t!=NULL){
      if(i++==0)
        nM=atoi(t);
      else
        nP=atoi(t);
      t=strtok(NULL," ");
    }

    getline(cin,s);
    int numbers[nM+1];
    numbers[0]=0;
    i=1;
    t=strtok(&s[0]," ");
    while(t!=NULL){
      numbers[i++]=atoi(t);
      t=strtok(NULL," ");
    }

    cout<<"result "<<n<<endl;

    partition(numbers,nM,nP);
    getline(cin,s);
    if(s!="-1")
      cout<<endl;
  } while(s!="-1");
  return 0;
}
