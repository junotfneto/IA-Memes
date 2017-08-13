#BASE 10

data <- read.csv("base-10.csv", header=FALSE)
library(stats)
library(cluster)

grupos <- data$V2
data$V1 <- NULL
data$V2 <- NULL
data$V2297 <- NULL

data <- data/255.0

clusters <- kmeans(data,4)

hc <- hclust(dist(data))
clusters2 <- cutree(hc, k = 4)

dissE <- daisy(data) #-> large (!)  3000 x 3000 / 2
sk <- silhouette(clusters$cluster, dissE) #KMEANS
sk <- silhouette(clusters2, dissE)
plot(sk)

########################################################################
#BASE 6

data <- read.csv("base-6.csv", header=FALSE)
library(stats)
library(cluster)

grupos <- data$V2
data$V1 <- NULL
data$V2 <- NULL
data$V6451 <- NULL

data <- data/255.0

for(i in 1:nrow(data)){
	for(j in 1:ncol(data)){
		if(is.na(data[i,j]))
			data[i,j] <- 0
		
	}
	print(i/nrow(data))
}

which(is.na(data) == TRUE)
########################################################################
#PRÉ PROCESSAMENTO
#FUNCAO DEGRAU
limite1 <- 0.5
limite2 <- 0.95
s1 <- which(data < limite1)
s2 <- which(data > limite2)

for(i in 1:nrow(data)){
	for(j in 1:ncol(data)){
		if(data[i,j] < limite1)
			data[i,j] <- 0
		if(data[i,j] > limite2)
			data[i,j] <- 1
	}
	print(i/nrow(data))
}

algorithm = c("Hartigan-Wong", "Lloyd", "Forgy", "MacQueen")

clusters <- kmeans(data,2, algorithm="Hartigan-Wong")

hc <- hclust(dist(data))
clusters2 <- cutree(hc, k = 2)

dissE <- daisy(data) #-> large (!)  3000 x 3000 / 2
sk <- silhouette(clusters$cluster, dissE) #KMEANS
sk <- silhouette(clusters2, dissE) #HIERARQUICO
plot(sk)

#############################################################################
#PCA

pca <- prcomp(data, center=TRUE, scale = TRUE)
plot(pca$x)

predict(pca, newdata=tail(data, 2))

############################################################################
#PLOT

library(devtools)
install_github("ggbiplot", "vqv")
 
library(ggbiplot)
g <- ggbiplot(pca, obs.scale = 1, var.scale = 1, 
              groups = grupos, ellipse = TRUE, 
              circle = TRUE)
g <- g + scale_color_discrete(name = '')
g <- g + theme(legend.direction = 'horizontal', 
               legend.position = 'top')
print(g)

library(scatterplot3d)
attach(pca$x)

#PARA SALVAR EM IMAGEM
for(i in 1:360){
png(paste(i,".png"))
scatterplot3d(pca$x[1,], pca$x[2,], pca$x[3,], xlim=c(-5,5), ylim=c(-5,5), zlim=c(-5,5), angle=i, main="3D Scatterplot")
dev.off()
}

#PLOTAR ANIMAÇÃO
for(i in 1:360){
scatterplot3d(pca$x[1,], pca$x[2,], pca$x[3,], xlim=c(-5,5), ylim=c(-5,5), zlim=c(-5,5), angle=i, main="3D Scatterplot")
}