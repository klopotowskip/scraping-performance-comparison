import pandas as pd
import matplotlib.pyplot as plt

df = pd.read_csv("results.csv")

df[df.columns[1]] = df[df.columns[1]].astype(float) / 1000.0
df[df.columns[2]] = df[df.columns[2]].astype(float) / 1000.0

plt.figure(figsize=(10, 6))
plt.plot(df.iloc[:, 0], df.iloc[:, 1], linestyle='-', color='b', label='Selenium')
plt.plot(df.iloc[:, 0], df.iloc[:, 2], linestyle='-', color='r', label='JSoup')

plt.xlabel("# of attempt")
plt.ylabel("Execution time [s]")
plt.title("Time comparison of scraping tools: Selenium vs JSoup")
plt.legend()
plt.grid(True)

mean_selenium = df.iloc[:, 1].mean()
mean_jsoup = df.iloc[:, 2].mean()

print(f"Mean selenium = {mean_selenium} s")
print(f"Mean jsoup = {mean_jsoup} s")

plt.axhline(mean_selenium, color='b', linestyle=':', label='Mean Time Selenium')
plt.axhline(mean_jsoup, color='r', linestyle=':', label='Mean Time JSoup')

plt.xlim(1, 100)
plt.ylim(0, 10)

plt.savefig("plot.jpg", format="jpg", dpi=300)
plt.show()